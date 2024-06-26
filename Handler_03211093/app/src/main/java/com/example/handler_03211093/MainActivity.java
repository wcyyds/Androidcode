package com.example.handler_03211093;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg){
            int a = msg.arg1;
            int b = msg.arg2;
            String s = msg.obj.toString();
            Bundle bundle = msg.getData();
            String str = bundle.getString("name") + bundle.getInt("age") + bundle.getString("sex");
            tv1.setText(a + "" + b + s + str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textView1);
    }

    public void clickl(View ciew) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.arg1 = 1;
                msg.arg2 = 2;
                msg.obj = "hello";
                Bundle bundle = new Bundle();
                bundle.putString("name", "tom");
                bundle.putInt("age", 20);
                bundle.putString("sex", "male");
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();
    }
}