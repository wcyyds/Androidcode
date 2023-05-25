package com.example.viewbindinglearntest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//首先就是在Adapter中使用ViewBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}