package com.example.remote_help.sharescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.remote_help.MainActivity;
import com.example.remote_help.R;

import java.util.concurrent.BlockingDeque;

public class GetPhoneNumberActivity extends AppCompatActivity {

    private Person person = Person.getInstance();

    private Button button;

    private EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);

        isPersonData();

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里可以加一个限制手机号的判断的东西
                person.setUserID(editText.getText().toString());
                person.setRoomID(editText.getText().toString());
                person.setStreamID(editText.getText().toString());

                Log.d("chat", "onClick: 检查" + person.getUserID());

                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("userid", person.getUserID());
                editor.apply();

                Log.d("123", "onClick: 添加成功");
                startMainActivity();
            }
        });

    }

    private void isPersonData(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid", "");
        Log.d("start", "isPersonData: 开机检测是否收到,本地传来的值" + userid);


        //这个现在不能读取
        if(!(userid == null)){
            Log.d("进入到要电话号码", "isPersonData: " + userid);
            startMainActivity();
        }
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //finish();
    }
}