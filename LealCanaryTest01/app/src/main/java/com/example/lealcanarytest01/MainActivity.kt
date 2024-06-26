package com.example.lealcanarytest01

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import leakcanary.LeakCanary
import java.lang.Exception

val handler = object : Handler(Looper.getMainLooper()){
    override fun handleMessage(msg: Message) {
        when(msg.what){
            1 -> Log.d("111","你好")
        }
    }
}

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Runnable {
            val msg = Message()
            msg.what = 1
            handler.sendMessage(msg)
            Log.d("111","开始发送")
        }.run()

    }
}