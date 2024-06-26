package com.example.data_simu_03211093;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "**data_simu**";

    Socket socket;

    OutputStream outputStream;

    InputStream inputStream;

    Random random = new Random();

    Handler lightRelayHandler;

    ImageView lightR;

    ImageView lightW;

    ImageView lightG;

    ImageView lightY;

    ImageView relay;

    TextView moneytext;

    ImageView lightView;

    byte[] illBytes = {(byte) 0xEE, (byte) 0xCC, (byte) 0x01, (byte) 0x02,
            (byte) 0x01, (byte) 0x11, (byte) 0x11, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF};

    // EE CC 01 02 01 代表温湿度
    byte[] temHumByte = {(byte) 0xEE, (byte) 0xCC, (byte) 0x01, (byte) 0x03,
            (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF};

    byte[] randomBytes = {(byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x10, (byte) 0x11, (byte) 0x12,
            (byte) 0x13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lightR = this.findViewById(R.id.control_light_img_red);
        lightW = this.findViewById(R.id.control_light_img_white);
        lightG = this.findViewById(R.id.control_light_img_green);
        lightY = this.findViewById(R.id.control_light_img_yellow);
        relay = this.findViewById(R.id.control_relay_img);
        moneytext = this.findViewById(R.id.moneytext);
        lightView = this.findViewById(R.id.lightview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    initSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startDataFakerTimer();

                startListenServerThrean();
            }
        }).start();

        lightRelayHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Log.d(TAG, "handleMessage: 在这里接收了消息！" + msg.what);
                switch (msg.what) {
                    //开红灯
                    case 1:
                        lightR.setBackgroundResource(R.mipmap.c_light_red);
                        break;
                    case 2:
                        lightR.setBackgroundResource(R.mipmap.c_light_off);
                        break;
                    case 3:
                        lightW.setBackgroundResource(R.mipmap.c_light_white);
                        break;
                    case 4:
                        lightW.setBackgroundResource(R.mipmap.c_light_off);
                        break;
                    case 5:
                        lightG.setBackgroundResource(R.mipmap.c_light_green);
                        break;
                    case 6:
                        lightG.setBackgroundResource(R.mipmap.c_light_off);
                        break;
                    case 7:
                        lightY.setBackgroundResource(R.mipmap.c_light_yellow);
                        break;
                    case 8:
                        lightY.setBackgroundResource(R.mipmap.c_light_off);
                        break;
                    case 9:
                        lightR.setBackgroundResource(R.mipmap.c_light_red);
                        lightW.setBackgroundResource(R.mipmap.c_light_white);
                        lightG.setBackgroundResource(R.mipmap.c_light_green);
                        lightY.setBackgroundResource(R.mipmap.c_light_yellow);
                        break;
                    case 10:
                        lightR.setBackgroundResource(R.mipmap.c_light_off);
                        lightW.setBackgroundResource(R.mipmap.c_light_off);
                        lightG.setBackgroundResource(R.mipmap.c_light_off);
                        lightY.setBackgroundResource(R.mipmap.c_light_off);
                        break;
                    case 11:
                        relay.setBackgroundResource(R.mipmap.c_relay_on);
                        break;
                    case 12:
                        relay.setBackgroundResource(R.mipmap.c_relay_off);
                        break;
                    case 13:
                        moneytext.setText(String.valueOf(msg.arg1));
                        break;
                    case 14:
                        lightView.setBackgroundResource(R.mipmap.light_001);
                        break;
                    case 15:
                        lightView.setBackgroundResource(R.mipmap.light_002);
                        break;
                    case 16:
                        lightView.setBackgroundResource(R.mipmap.light_003);
                        break;
                    case 17:
                        lightView.setBackgroundResource(R.mipmap.light_004);
                        break;
                }
                return false;
            }
        });

    }

    private void initSocket() throws IOException {
        try {
            socket = new Socket("localhost", 7777);
            Log.d(TAG, "initSocket: 连接成功");
        } catch (NetworkOnMainThreadException e) {
            Log.d(TAG, "initSocket: 连接失败");
            e.printStackTrace();
        }
        if (socket != null) {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        }
    }

    public void startDataFakerTimer() {
        if (socket != null) {
            long delay = 3000;
            long period = 1000;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    int randomInt = random.nextInt(12);
                    illBytes[5] = temHumByte[5] = temHumByte[8] = randomBytes[randomInt];
                    randomInt = random.nextInt(12);
                    illBytes[6] = temHumByte[6] = temHumByte[7] = randomBytes[randomInt];
                    try {
                        sendMsg(illBytes);
                        sendMsg(temHumByte);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, delay, period);
        }
    }

    private void startListenServerThrean() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] socketCmd = getMsg();
                        Log.d(TAG, "run: " + Arrays.toString(socketCmd));
                        Message message = new Message();
                        if (socketCmd[3] == (byte) 0x09) {
                            switch (socketCmd[4]) {
                                case (byte) 0x01:
                                    message.what = 1;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("红灯开");
                                    break;
                                case (byte) 0x02:
                                    message.what = 2;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("红灯关");
                                    break;
                                case (byte) 0x03:
                                    message.what = 3;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("白灯开");
                                    break;
                                case (byte) 0x04:
                                    message.what = 4;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("白灯关");
                                    break;
                                case (byte) 0x05:
                                    message.what = 5;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("绿灯开");
                                    break;
                                case (byte) 0x06:
                                    message.what = 6;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("绿灯关");
                                    break;
                                case (byte) 0x07:
                                    message.what = 7;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("白灯开");
                                    break;
                                case (byte) 0x08:
                                    message.what = 8;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("白灯关");
                                    break;
                                case (byte) 0x0c:
                                    message.what = 9;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("全部灯开");
                                    break;
                                case (byte) 0x0d:
                                    message.what = 10;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("全部灯关");
                                    break;
                            }
                        } else if (socketCmd[3] == (byte) 0x18) {
                            switch (socketCmd[4]) {
                                case (byte) 0x01:
                                    message.what = 11;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("继电器开启");
                                    break;
                                case (byte) 0x02:
                                    message.what = 12;
                                    lightRelayHandler.sendMessage(message);
                                    System.out.println("继电器关闭");
                                    break;
                            }
                        } else if (socketCmd[3] == (byte) 0x19) {
                            //将int值转化为四个字节进行传送
                            message.what = 13;
                            int reassembledInt = ((socketCmd[4] & 0xFF) << 24 |
                                                    (socketCmd[5] & 0xFF) << 16 |
                                                    (socketCmd[6] & 0xFF) << 8 |
                                                    (socketCmd[7] & 0xFF));
                            message.arg1 = reassembledInt;
                            lightRelayHandler.sendMessage(message);
                            //在cmd[4]中传number
                        }else if(socketCmd[3] == (byte) 0x20){
                            switch (socketCmd[4]){
                                case (byte) 0x01:
                                    message.what = 14;
                                    lightRelayHandler.sendMessage(message);
                                    //灭
                                    break;
                                case (byte) 0x02:
                                    message.what = 15;
                                    lightRelayHandler.sendMessage(message);
                                    //微亮
                                    break;
                                case (byte) 0x03:
                                    message.what = 16;
                                    lightRelayHandler.sendMessage(message);
                                    //较亮
                                    break;
                                case (byte) 0x04:
                                    message.what = 17;
                                    lightRelayHandler.sendMessage(message);
                                    //亮
                                    break;
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        ;
                    }
                }
            }
        }).start();
    }


    public void sendMsg(byte[] msg) throws IOException {
        outputStream.write(msg);
        outputStream.flush();
    }

    public byte[] getMsg() throws IOException {
        byte[] buffer = new byte[16];
        int size = inputStream.read(buffer);
        if (size > 0) {
            return buffer;
        }
        return null;
    }

}