package com.example.mytalk02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText host;

    private EditText port;

    private EditText name;

    private Button login;

     private Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        host = (EditText) findViewById(R.id.host);
        port = (EditText) findViewById(R.id.port);
        name = (EditText) findViewById(R.id.name);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        Log.d("Login", "点击按钮的触发");
        /*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();


//                //如果账号是admin且密码是123456,就认为登录成功
//                if(account.equals("admin") && password.equals("123456")){
//                    Log.d("login", "发出广播");
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(LoginActivity.this,
//                            "account or password is invalid", Toast.LENGTH_SHORT).show();
//                }
            }
        });

         */

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String Host = host.getText().toString();
                String Port = port.toString().toString();
                String Name = name.toString().toString();
                Log.d("Login", "在创建线程之前");
                new Thread(){
                    {
                        try {
                            socket = new Socket(Host, Integer.parseInt(Port));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    };
                };
                Log.d("Login", "已经创建了一个线程了");
                Intent intent = new Intent(LoginActivity.this, RecyclerView.class);
                startActivity(intent);
                break;
        }
    }
}
