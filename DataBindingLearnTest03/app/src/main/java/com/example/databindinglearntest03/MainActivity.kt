package com.example.databindinglearntest03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindinglearntest03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 设置布局文件
        // 布局文件是 activity_main.xml
        // 该类名称生成规则是 布局文件名称 + Binding
        var binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        var swordsman = Swordsman("zhang", "s")
        binding.


    }
}