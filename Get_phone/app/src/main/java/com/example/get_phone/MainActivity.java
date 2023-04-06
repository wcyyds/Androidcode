package com.example.get_phone;

import static android.Manifest.permission.READ_CALL_LOG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 获取来电号码
     */
    private Button btnIncomingCall;

    /**
     * 不获取来电号码
     */
    private Button btnIncomingCallCancel;

    /**
     * 获取去电号码
     */
    private Button btnOutgoingcall;

    /**
     * 不获取去电号码
     */
    private Button btnOutgoingCallCancel;

    private Button button3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        // 实例化控件
        btnIncomingCall = (Button) findViewById(R.id.btn_incoming_call);
        btnIncomingCallCancel = (Button) findViewById(R.id.btn_incoming_call_cancel);
        btnOutgoingcall = (Button) findViewById(R.id.btn_outgoing_call);
        btnOutgoingCallCancel = (Button) findViewById(R.id.btn_outgoing_call_cancel);
        button3 = (Button) findViewById(R.id.button3);

        // 设置按键监听
        btnIncomingCall.setOnClickListener(this);
        btnIncomingCallCancel.setOnClickListener(this);
        btnOutgoingcall.setOnClickListener(this);
        btnOutgoingCallCancel.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    //请求摄像头、录音权限
    private void requestPermission() {
        String[] permissionNeeded = {
                "android.permission.READ_CALL_LOG",
                "android.permission.READ_PHONE_STATE",
                "android.permission.CALL_PHONE"};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_CALL_LOG") != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PHONE_STATE") != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CALL_PHONE") != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionNeeded, 101);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_incoming_call:
                Intent startIncomingService = new Intent(this, IncomingCallService.class);
                startService(startIncomingService);
                Toast.makeText(this, "获取来电号码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_incoming_call_cancel:
                Intent stopIncomingService = new Intent(this, IncomingCallService.class);
                stopService(stopIncomingService);
                Toast.makeText(this, "不获取来电号码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_outgoing_call:
                Intent startOutgoingService = new Intent(this, OutgoingCallService.class);
                startService(startOutgoingService);
                Toast.makeText(this, "获取去电号码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_outgoing_call_cancel:
                Intent stopOutgoingService = new Intent(this, OutgoingCallService.class);
                stopService(stopOutgoingService);
                Toast.makeText(this, "不获取去电号码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                callPhone("18991377839");
        }
    }

    /**

     * 拨打电话(直接拨打电话)

     * @param phoneNum 电话号码

     */

    public void callPhone(String phoneNum){

        Intent intent = new Intent(Intent.ACTION_CALL);

        Uri data = Uri.parse("tel:" + phoneNum);

        intent.setData(data);

        startActivity(intent);

    }
}