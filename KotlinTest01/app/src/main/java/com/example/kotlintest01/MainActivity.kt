package com.example.kotlintest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlintest01.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    //在onCreate()函数之外的地方对控件进行操作，那么就得将binding变量声明成全局变量
    //kotlin声明的变量都必须在声明的同时进行初始化
    //这里我们不能在声明binding变量的同时对它进行初始化,所以通过lateinit进行了延迟初始化
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener(){
            Toast.makeText(this, "This is a Toast", Toast.LENGTH_SHORT).show()
        }
    }




}