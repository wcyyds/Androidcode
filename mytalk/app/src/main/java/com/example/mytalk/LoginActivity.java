package com.example.mytalk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText host = (EditText) findViewById(R.id.host);
        EditText port = (EditText) findViewById(R.id.port);
        EditText name = (EditText) findViewById(R.id.name);
        login = (Button) findViewById(R.id.login);
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

    }
}
