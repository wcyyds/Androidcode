package com.example.evenbuslearntest01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import com.example.evenbuslearntest01.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSubscription.setOnClickListener{
            Log.d("MainActiviy", "btSubscription")
            EventBus.getDefault().register(this)
        }

        binding.btMessage.setOnClickListener {
            Log.d("MainActiviy", "btMessage")
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public fun onMoonEvent(messageEvent: MessageEvent) {
        Log.d("MainActiviy", "onMoonEvent" + messageEvent.message+ "1111")
        binding.tvMessage.setText(messageEvent.message)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}