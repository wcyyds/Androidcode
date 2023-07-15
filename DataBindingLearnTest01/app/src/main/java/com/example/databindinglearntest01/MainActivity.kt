package com.example.databindinglearntest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.databindinglearntest01.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var dataBindingActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //使用DataBindingUtil将布局与activity进行绑定
        dataBindingActivity = DataBindingUtil.setContentView(this,
        R.layout.activity_main)


        dataBindingActivity.imageNetwork = "https://img-blog.csdnimg.cn/0d611b315e8448f7a01f7a772c238c6f.png"
        dataBindingActivity.imageLocal = R.mipmap.ic_launcher

    }


}
