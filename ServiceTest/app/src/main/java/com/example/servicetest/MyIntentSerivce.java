package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentSerivce extends IntentService {

    public MyIntentSerivce() {
        super("MyIntentService");//调用父类的有参构造函数
    }

    /**
     * @param name
     * @deprecated
     */
    public MyIntentSerivce(String name) {
        super(name);//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的ID
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestory executed");
    }
}
