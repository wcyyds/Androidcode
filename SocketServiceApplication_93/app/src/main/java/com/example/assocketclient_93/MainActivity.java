package com.example.assocketclient_93;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.SocketServiceApplication_93.R;

public class MainActivity extends AppCompatActivity {

    private EditText et_content, et_name;
    private TextView tv_se;
    private MyAPP myAPP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("**SOCKET1", "onCreate: nihao");
        et_content = (EditText) findViewById(R.id.et_content);
        tv_se = (TextView) findViewById(R.id.tv_se);
        et_name = (EditText) findViewById(R.id.et_name);
        myAPP = (MyAPP) getApplication();

    }

    public void click1(View view) {
        Log.d("**SOCKET1", "启动服务器");
        myAPP.setOnConnectLinstener(new MyAPP.ConnectLinstener() {
            @Override
            public void onReceiveData(String data) {
                Log.d("**SOCKET12", "服务端接受的消息" + data);
                tv_se.append(data + "\n");
            }
        });
    }

    public void click2(View view) {
        Log.d("**SOCKET1", "发送信息");
        String name = et_name.getText().toString();
        String a = name + ":" + et_content.getText().toString();
        tv_se.append(a + "\n");
        Log.d("**SOCKET1", "DATA" + a);
        myAPP.send(a);
    }
}