package com.example.testdemo333;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.callback.IZegoDestroyCompletionCallback;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.constants.ZegoPlayerState;
import im.zego.zegoexpress.constants.ZegoPublishChannel;
import im.zego.zegoexpress.constants.ZegoPublisherState;
import im.zego.zegoexpress.constants.ZegoRoomStateChangedReason;
import im.zego.zegoexpress.constants.ZegoScenario;
import im.zego.zegoexpress.constants.ZegoStreamQualityLevel;
import im.zego.zegoexpress.constants.ZegoUpdateType;
import im.zego.zegoexpress.constants.ZegoVideoSourceType;
import im.zego.zegoexpress.entity.ZegoCanvas;
import im.zego.zegoexpress.entity.ZegoEngineProfile;
import im.zego.zegoexpress.entity.ZegoRoomConfig;
import im.zego.zegoexpress.entity.ZegoStream;
import im.zego.zegoexpress.entity.ZegoUser;

public class MainActivity extends AppCompatActivity {

    Person person = Person.getInstance();

    private EditText userid;
    private EditText roomid;
    private EditText  streamid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userid = (EditText) findViewById(R.id.userid);

        roomid = (EditText) findViewById(R.id.roomid);

        streamid = (EditText) findViewById(R.id.streamid);

        //每个项目独有的id
        person.setAppID(609408564L);
        //每个人独有的id
        person.setUserID(userid.getText().toString());
        //房间名不能重复，只能两个人使用
        person.setRoomID(roomid.getText().toString());
        //每个人独有的流的id，重复使用会把前面一个人挤出去
        person.setStreamID(streamid.getText().toString());
        Log.d("1111", "onCreate: " + userid.getText().toString());
        Log.d("1111", "onCreate: " + roomid.getText().toString());
        Log.d("1111", "onCreate: " + streamid.getText().toString());
        //项目的鉴权秘钥
        person.setAppSign("59fb6b3d70836007ba898832b22da9917c76576b26436737a218dbd538eb33d7");

        Button helped = (Button) findViewById(R.id.helped);
        helped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //每个项目独有的id
                person.setAppID(609408564L);
                //每个人独有的id
                person.setUserID(userid.getText().toString());
                //房间名不能重复，只能两个人使用
                person.setRoomID(roomid.getText().toString());
                //每个人独有的流的id，重复使用会把前面一个人挤出去
                person.setStreamID(streamid.getText().toString());
                Intent intent = new Intent(MainActivity.this, Helped_person.class);
                startActivity(intent);
            }
        });

        Button helper = (Button) findViewById(R.id.helper);
        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //每个项目独有的id
                person.setAppID(609408564L);
                //每个人独有的id
                person.setUserID(userid.getText().toString());
                //房间名不能重复，只能两个人使用
                person.setRoomID(roomid.getText().toString());
                //每个人独有的流的id，重复使用会把前面一个人挤出去
                person.setStreamID(streamid.getText().toString());
                Intent intent = new Intent(MainActivity.this, Helper.class);
                startActivity(intent);
            }
        });
    }
}