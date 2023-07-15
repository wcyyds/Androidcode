package com.example.databindinglearntest02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindinglearntest02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局文件
        // 布局文件是 activity_main.xml
        // 该类名称生成规则是 布局文件名称 + Binding
        var activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 为布局 设置 数据
        activityMainBinding.imageNetwork = "https://img-blog.csdnimg.cn/0d611b315e8448f7a01f7a772c238c6f.png"
        activityMainBinding.imageLocal = R.mipmap.ic_launcher

    }
}