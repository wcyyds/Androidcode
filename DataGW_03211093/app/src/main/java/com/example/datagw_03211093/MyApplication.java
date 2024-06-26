package com.example.datagw_03211093;

import static com.example.datagw_03211093.MainActivity.setTemHumValueHandler;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class MyApplication extends Application {

    public static String TAG = "**data_gw**my_application";
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static OutputStream outputStream;
    private ConnectListener setIllListener;
    private ConnectListener setTemHumListener;
    final LinkedList<Socket> sockets = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyApplicationOnCreate@Process" + Process.myPid());
        Log.d(TAG, "MyApplicationOnCreate@Thread" + Thread.currentThread().getId());

        ServerListeners serverListeners = new ServerListeners();
        serverListeners.start();

    }


    public interface ConnectListener {
        void onReceiveData(byte[] buffer);
    }

    public void setSetIllListenerOnConnected(ConnectListener listener) {
        this.setIllListener = listener;
    }

    public void setTemHumListenerOnConnected(ConnectListener listener) {
        this.setTemHumListener = listener;
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            //Log.d(TAG, "mHandlerHandleMsg@Process" + Process.myPid());
            //Log.d(TAG, "mHandlerHandleMsg@Thread" + Thread.currentThread().getId());

            byte[] data = message.getData().getByteArray("data");
            switch (message.what) {
                case 100:
                    if (setIllListener != null) setIllListener.onReceiveData(data);
                    break;
                case 200:
                    if (setTemHumListener != null) setTemHumListener.onReceiveData(data);
                    break;
            }
            return true;
        }
    });

    class SocketTask implements Runnable {
        private Socket socket;

        public SocketTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            Log.d(TAG, "SoketTaskRun@Process" + Process.myPid());
            Log.d(TAG, "SoketTaskRun@Thread" + Thread.currentThread().getId());
            while (true) {
                int size;
                try {
                    InputStream inputStream = socket.getInputStream(); //输入流
                    byte[] buffer = new byte[16];
                    size = inputStream.read(buffer);
                    if (size > 0) {
                        Message message = new Message();
                        if (buffer[3] == (byte) 0x02 && buffer[4] == (byte) 0x01) {
                            // 光照
                            message.what = 100;
                        } else if (buffer[3] == (byte) 0x03 && buffer[4] == (byte) 0x01) {
                            // 温度湿度
                            message.what = 200;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putByteArray("data", buffer);

                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public class ServerListeners extends Thread {    //socket监听线程
        @Override
        public void run() {
            try {
                // 监听端口7777
                Log.d(TAG, "ServerListenersRun@Process " + Process.myPid());
                Log.d(TAG, "ServerListenersRun@Thread " + Thread.currentThread().getId());
                ServerSocket serverSocket = new ServerSocket(7777);
                while (true) {
                    Log.d(TAG, "等待下一个客户端请求....");
                    //  --------------socket通信--------
                    Socket socket = serverSocket.accept();
                    Log.d(TAG, "收到请求，服务器建立连接...");
                    Log.d(TAG, "客户端" + socket.getInetAddress().getHostAddress() + "连接成功");
                    Log.d(TAG, "客户端" + socket.getRemoteSocketAddress() + "连接成功");
                    sockets.add(socket);
                    //每次都启动一个新的线程
                    new Thread(new SocketTask(socket)).start();   //启动接收子线程
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * 发送数据
     *
     * @param bytes 需要发送的内容
     */
    public void send(final byte[] bytes) {     //发送数据函数，供主程序调用
        Log.d(TAG, "当前连接数" + sockets.size());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "SocketSendProcess " + Process.myPid());
                Log.d(TAG, "SocketSendThread" + Thread.currentThread().getId());
                for (Socket s : sockets) {
                    Log.d(TAG, "客户端" + s.getRemoteSocketAddress());
                    try {
                        outputStream = s.getOutputStream();
                        if (outputStream != null) {

                            outputStream.write(bytes);
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, "run: 连接出错");
                    }
                }
            }
        }).start();
    }

    /**
     * 断开连接
     *
     * @throws IOException IO异常
     */
    public void disconnect() throws IOException {

        Log.d(TAG, "客户端是否关闭1");
        if (sockets.size() != 0) {
            for (Socket s : sockets) {
                s.close();

                Log.d(TAG, "客户端是否关闭2");

            }
        }
        if (outputStream != null)
            outputStream.close();

        sockets.clear();
    }

}
