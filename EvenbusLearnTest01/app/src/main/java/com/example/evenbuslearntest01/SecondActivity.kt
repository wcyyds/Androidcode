package com.example.evenbuslearntest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.evenbuslearntest01.databinding.ActivityMainBinding
import com.example.evenbuslearntest01.databinding.ActivitySecondBinding
import org.greenrobot.eventbus.EventBus

class SecondActivity : AppCompatActivity() {

    private lateinit var bindind1 : ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind1 = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindind1.root)
        bindind1.btSendmessage.setText("这是第二个活动")
        bindind1.btSendmessage.setOnClickListener {
            val messageEvent = MessageEvent("NIHAO")
            EventBus.getDefault().postSticky(messageEvent)
            finish()
        }
    }
}