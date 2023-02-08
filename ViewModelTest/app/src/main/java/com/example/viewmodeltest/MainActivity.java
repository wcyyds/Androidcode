package com.example.viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniComponent();
    }

    private void iniComponent()
    {
        //这里使用了SharedPreferences来存储,最后关闭程序,跑到最后的秒数
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        int currentSecond = 0;
        if(!sharedPreferences.getString("data","").equals("")){
            currentSecond = Integer.parseInt(sharedPreferences.getString("data",""));
        }

        TextView tvTime = findViewById(R.id.tvTime);
        //注意这里是创建了一个工厂类TimerViewModelFactory
        //我们来借助工厂类,构造ViewModle的具体事例,工厂类会在合适的时机创建ViewModle实例
        TimerViewModel timerViewModel = new ViewModelProvider(this,
                new TimerViewModelFactory(currentSecond)).get(TimerViewModel.class);

        //这里使用observe观察类,来监听LiveData中传过来的数据,并改变他
        timerViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvTime.setText("TIME: " + s);
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("data", s);
                editor.apply();
            }
        });
        timerViewModel.startTiming();
    }
}