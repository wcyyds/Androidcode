package com.example.datagw_03211093;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "**data_gw**";
    public static Handler setTemHumValueHandler = null;
    private Handler touchLightHandler = null;
    private String deviceId = "001";
    private boolean hardwareModeFlag = false;
    private boolean relayFlag = false;

    private MyApplication myApplication;

    private InputStream inputStreamChuanKou;//串口输入流
    private OutputStream outputStreamChuanKou;//串口输出流
    private TextView wendu, shidu, guangzhaodu;
    private ImageButton jidianqiBtn;

    private Button addbutton, delbutton;

    private EditText numberView;
    private int number;

    private Button light_001, light_002, light_003, light_004;

    private ImageView lightview;

    //---------------------- 增加led控制相关变量 -------------------
    private ImageView lightR, lightW, lightG, lightY, all_light;
    private int num1 = 0;//灯
    private int num2 = 0;
    private int num3 = 0;
    private int num4 = 0;
    private int state1 = 0;
    private int state2 = 0;
    private int state3 = 0;
    private int state4 = 0;
    private String operation;  //用于存放led控制指令
    //------------------------------------------------------------


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        wendu = findViewById(R.id.temp);
        shidu = findViewById(R.id.hehum);
        guangzhaodu = findViewById(R.id.guangzhao);
        jidianqiBtn = findViewById(R.id.bt1);
        addbutton = findViewById(R.id.addmoney);
        delbutton = findViewById(R.id.delmoney);
        numberView = findViewById(R.id.number);
        light_001 = findViewById(R.id.light_bt_one);
        light_002 = findViewById(R.id.light_bt_two);
        light_003 = findViewById(R.id.light_bt_three);
        light_004 = findViewById(R.id.light_bt_four);
        lightview = findViewById(R.id.lightView);

        myApplication = (MyApplication) getApplication();
        initAll();

        jidianqiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRelayCtrlClick();
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoneyNumber(true);
            }
        });

        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoneyNumber(false);
            }
        });

        light_001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight((byte) 0x01);
                lightview.setBackgroundResource(R.mipmap.light_001);
            }
        });

        light_002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight((byte) 0x02);
                lightview.setBackgroundResource(R.mipmap.light_002);
            }
        });

        light_003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight((byte) 0x03);
                lightview.setBackgroundResource(R.mipmap.light_003);
            }
        });

        light_004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLight((byte) 0x04);
                lightview.setBackgroundResource(R.mipmap.light_004);
            }
        });

    }

    private void initAll() {
        Log.d(TAG, "initAll: 无硬件环境");
        startFakeDataListener();
        startLightListener();
        startSetTemHumValueHandler();
        startIlluminationListener();
    }


    /**
     * 开启灯光控制监听器
     */
    private void startLightListener() {

        lightR = this.findViewById(R.id.control_light_img_red);
        lightW = this.findViewById(R.id.control_light_img_white);
        lightG = this.findViewById(R.id.control_light_img_green);
        lightY = this.findViewById(R.id.control_light_img_yellow);
        all_light = this.findViewById(R.id.control_relay_img);

        // 红灯
        lightR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1 == 0 && state1 == 0) {
                    operation = "1";
                    sendLightMsg(operation);
                    lightR.setBackgroundResource(R.mipmap.c_light_red);
                    num1 = 1;
                    state1 = 1;
                } else if (num1 == 1 && state1 == 1) {
                    operation = "2";
                    sendLightMsg(operation);
                    lightR.setBackgroundResource(R.mipmap.c_light_off);
                    num1 = 0;
                    state1 = 0;
                }
                operation = "";
            }
        });
        // 白灯
        lightW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num2 == 0 && state2 == 0) {
                    operation = "3";
                    sendLightMsg(operation);
                    lightW.setBackgroundResource(R.mipmap.c_light_white);
                    state2 = 1;
                    num2 = 1;
                } else if (num2 == 1 && state2 == 1) {
                    operation = "4";
                    sendLightMsg(operation);
                    lightW.setBackgroundResource(R.mipmap.c_light_off);
                    state2 = 0;
                    num2 = 0;
                }
                operation = "";
            }
        });
        // 绿灯
        lightG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num3 == 0 && state3 == 0) {
                    operation = "5";
                    sendLightMsg(operation);
                    lightG.setBackgroundResource(R.mipmap.c_light_green);
                    state3 = 1;
                    num3 = 1;
                } else if (num3 == 1 && state3 == 1) {
                    operation = "6";
                    sendLightMsg(operation);
                    lightG.setBackgroundResource(R.mipmap.c_light_off);
                    state3 = 0;
                    num3 = 0;
                }
                operation = "";
            }
        });
        // 黄灯
        lightY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num4 == 0 && state4 == 0) {
                    operation = "7";
                    sendLightMsg(operation);
                    lightY.setBackgroundResource(R.mipmap.c_light_yellow);
                    state4 = 1;
                    num4 = 1;
                } else if (num4 == 1 && state4 == 1) {
                    operation = "8";
                    sendLightMsg(operation);
                    lightY.setBackgroundResource(R.mipmap.c_light_off);
                    state4 = 0;
                    num4 = 0;
                }
                operation = "";
            }
        });
        // 全部灯
        all_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num4 == 0 && state4 == 0) {
                    operation = "9";
                    sendLightMsg(operation);
                    all_light.setBackgroundResource(R.mipmap.c_relay_on);
                    lightR.setBackgroundResource(R.mipmap.c_light_red);
                    lightW.setBackgroundResource(R.mipmap.c_light_white);
                    lightG.setBackgroundResource(R.mipmap.c_light_green);
                    lightY.setBackgroundResource(R.mipmap.c_light_yellow);
                    state1 = num1 = state2 = num2 = state3 = num3 = state4 = num4 = 1;
                } else if (num4 == 1 && state4 == 1) {
                    operation = "10";
                    sendLightMsg(operation);
                    all_light.setBackgroundResource(R.mipmap.c_relay_off);
                    lightR.setBackgroundResource(R.mipmap.c_light_off);
                    lightW.setBackgroundResource(R.mipmap.c_light_off);
                    lightG.setBackgroundResource(R.mipmap.c_light_off);
                    lightY.setBackgroundResource(R.mipmap.c_light_off);
                    state1 = num1 = state2 = num2 = state3 = num3 = state4 = num4 = 0;
                }
                operation = "";
            }
        });
    }

    /**
     * 开启虚拟数据监听器
     */
    private void startFakeDataListener() {
        myApplication.setTemHumListenerOnConnected(new MyApplication.ConnectListener() {
            @Override
            //接收到socket上的温度湿度数据
            public void onReceiveData(byte[] buffer) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putByteArray("data", buffer);
                message.setData(bundle);
                setTemHumValueHandler.sendMessage(message);
            }
        });
    }

    //开启光照度监听器
    private void startIlluminationListener() {
        myApplication.setSetIllListenerOnConnected(new MyApplication.ConnectListener() {
            @Override
            //接收到wifi socket上的光照度数据
            public void onReceiveData(byte[] buffer) {
                if (buffer[3] == (byte) 0x02 && buffer[4] == (byte) 0x01) {
                    byte byteXH = buffer[5];
                    byte byteXL = buffer[6];
                    String gzText = "光照度：" + (byteXH * 256 + byteXL);
                    guangzhaodu.setText(gzText);
                }
            }
        });
    }

    public void sendLightMsg(String operation) {
        // 默认数据 (byte)是进行强制类型转换
        byte[] cmd = {(byte) 0xCC, (byte) 0xEE, (byte) 0x01, (byte) 0x09,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF};

        if (operation.equalsIgnoreCase("1")) {// 红灯开
            cmd[4] = (byte) 0x01;
        } else if (operation.equalsIgnoreCase("2")) {// 红灯关
            cmd[4] = (byte) 0x02;
        } else if (operation.equalsIgnoreCase("3")) {// 黄灯开
            cmd[4] = (byte) 0x03;
        } else if (operation.equalsIgnoreCase("4")) {// 黄灯关
            cmd[4] = (byte) 0x04;
        } else if (operation.equalsIgnoreCase("5")) {// 绿灯开
            cmd[4] = (byte) 0x05;
        } else if (operation.equalsIgnoreCase("6")) {// 绿灯关
            cmd[4] = (byte) 0x06;
        } else if (operation.equalsIgnoreCase("7")) {// 白灯开
            cmd[4] = (byte) 0x07;
        } else if (operation.equalsIgnoreCase("8")) {// 白灯关
            cmd[4] = (byte) 0x08;
        } else if (operation.equalsIgnoreCase("9")) {// 全部灯开
            cmd[4] = (byte) 0x0c;
        } else if (operation.equalsIgnoreCase("10")) {// 全部灯关
            cmd[4] = (byte) 0x0d;
        }
        Log.d("PayActivity:", "cmd:" + Arrays.toString(cmd));
        myApplication.send(cmd);
    }

    public void onRelayCtrlClick() {
        byte[] cmd = {(byte) 0xCC, (byte) 0xEE, (byte) 0x01, (byte) 0x18,
                (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0xFF};
        if (relayFlag) {
            // 关闭继电器
            relayFlag = false;
            jidianqiBtn.setBackgroundResource(R.mipmap.off);
            cmd[4] = (byte) 0x02;
        } else {
            // 开启继电器
            relayFlag = true;
            jidianqiBtn.setBackgroundResource(R.mipmap.on);
        }
        // 根据是否有硬件来判断使用串口发送数据还是通过Socket发送数据
        if (hardwareModeFlag) {
            try {
                outputStreamChuanKou.write(cmd);
                outputStreamChuanKou.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            myApplication.send(cmd);
        }
    }

    //启动温湿度数据处理器
    private void startSetTemHumValueHandler() {
        setTemHumValueHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                byte[] data = msg.getData().getByteArray("data");

                assert data != null;
                if (data[3] == (byte) 0x03 && data[4] == (byte) 0x01) {    //接收到温湿度数据
                    byte byteXH = data[5];
                    byte byteXL = data[6];
                    byte byteYH = data[7];
                    byte byteYL = data[8];
                    String wdText = "温度：" + (byteXH * 256 + byteXL) / 100;
                    String sdText = "湿度：" + (byteYH * 256 + byteYL) / 100;
                    wendu.setText(wdText);
                    shidu.setText(sdText);
                }
                return true;
            }
        });
    }

    //模拟RFID卡充钱，扣钱
    private void onMoneyNumber(boolean add_del){
        byte[] cmd = {(byte) 0xCC, (byte) 0xEE, (byte) 0x01, (byte) 0x19,
                (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0xFF};
        int add_del_number = Integer.parseInt(String.valueOf(numberView.getText()));
        if(add_del){
            number += add_del_number;
        }else{
            number -= add_del_number;
        }
        cmd[4] = (byte) (number >>> 24);
        cmd[5] = (byte) (number >>> 16);
        cmd[6] = (byte) (number >>> 8);
        cmd[7] = (byte) number;
        myApplication.send(cmd);
    }

    //模拟灯的四种状态
    private void onLight(byte light_brightness){
        byte[] cmd = {(byte) 0xCC, (byte) 0xEE, (byte) 0x01, (byte) 0x20,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0xFF};
        cmd[4] = light_brightness;
        myApplication.send(cmd);
    }
}