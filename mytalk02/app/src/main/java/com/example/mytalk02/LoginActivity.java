package com.example.mytalk02;

import static android.content.ContentValues.TAG;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        host = (EditText) findViewById(R.id.host);
        host.setOnClickListener(this);
        port = (EditText) findViewById(R.id.port);
        port.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name);
        name.setOnClickListener(this);
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
                String Name = name.toString();
                Log.d("Login", "在创建线程之前");
                Log.d("Login", "已经创建了一个线程了");
                Intent intent1 = new Intent(this, RecyclerView.class);
                intent1.putExtra("1",Name);
                startActivity(intent1);
                Log.d(TAG, "onClick: suoyijiumeiy");
                break;
        }
    }
}
