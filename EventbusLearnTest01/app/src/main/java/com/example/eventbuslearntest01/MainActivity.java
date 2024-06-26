package com.example.eventbuslearntest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        Thread Mthread = new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new EventMessage(2,"2"));
            }
        });
        Mthread.start();

    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void showEventMessage(EventMessage message){
        Log.d("111222",message.getType() + "o的k + " + message.getMessage() );
    }



}