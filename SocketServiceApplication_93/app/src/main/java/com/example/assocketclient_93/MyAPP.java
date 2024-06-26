package com.example.assocketclient_93;


import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MyAPP extends Application {

    public static String TAG = "**SOCKET1";
    //---------socket通信--------
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public static OutputStream outputStream;
    private static ConnectLinstener mListener;
    final LinkedList<Socket> list = new LinkedList<>();

    private HandlerThread mHandlerThread;
    private Handler mSubThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: nihaomyapp");
        ServerListeners listener1 = new ServerListeners();
        listener1.start();
        Log.d(TAG, "nihaomyapp002");
        initHandlerThread();
    }

    public class ServerListeners extends Thread {
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(7777);
                while (true) {
                    Log.d(TAG, "run: 等待客户端请求");
                    System.out.println("等待客户端请求......");
                    socket = serverSocket.accept();
                    System.out.println("收到请求，服务器建立连接....");
                    Log.d(TAG, "收到请求，服务器建立连接....");
                    System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "连接成功");
                    Log.d(TAG, "客户端" + socket.getInetAddress().getHostAddress() + "连接成功");
                    System.out.println("客户端" + socket.getRemoteSocketAddress() + "连接成功");
                    Log.d(TAG, "客户端" + socket.getRemoteSocketAddress() + "连接成功");
                    list.add(socket);
                    new Thread(new Task(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initHandlerThread() {
        mHandlerThread = new HandlerThread("handler_thread");
        mHandlerThread.start();
        Looper loop = mHandlerThread.getLooper();
        mSubThreadHandler = new Handler(loop) {
            public void handleMessage(Message msg) {
                writeMsg(msg.getData().getString("data1"));
            }
        };
    }

    class Task implements Runnable {
        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                int size;
                try {
                    InputStream inputStream = null;
                    inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    size = inputStream.read(buffer);
                    if (size > 0) {
                        if (buffer[0] != (byte) 0xEE) {
                            String data = new String(buffer, 0, size, "gbk");
                            Message message = new Message();
                            message.what = 100;
                            Bundle bundle = new Bundle();
                            bundle.putString("data", data);

                            message.setData(bundle);
                            mHandler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public interface ConnectLinstener {
        void onReceiveData(String data);
    }

    public void setOnConnectLinstener(ConnectLinstener linstener) {
        this.mListener = linstener;
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    if (mListener != null) {
                        mListener.onReceiveData(msg.getData().getString("data"));
                    }
                    break;
            }
        }
    };

    public void send(String bytes) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data1", bytes);
        msg.setData(bundle);
        mSubThreadHandler.sendMessage(msg);
    }

    private void writeMsg(String msg) {
        Log.d(TAG, "服务端收到的socket" + msg);
        for (Socket s : list) {
            System.out.println("客户端" + s.getInetAddress().getHostAddress());
            Log.d(TAG, "writeMsg: 客户端" + s.getInetAddress().getHostAddress());
            try {
                outputStream = s.getOutputStream();
                if (outputStream != null) {
                    outputStream.write(msg.getBytes("gbk"));
                    Log.d(TAG, "writeMsg: 服务端发送的数据" + msg.getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("客户端socket不存在。");
            }
        }
    }

    public void disconnect() throws IOException {
        System.out.println("客户端是否关闭1");
        if (list.size() != 0) {
            for (Socket s : list) {
                s.close();
                System.out.println("客户端是否关闭2");
            }
        }
        if (outputStream != null)
            outputStream.close();
        list.clear();
    }
}
