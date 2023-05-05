package com.example.jinjie_viewtest01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    CustomView customView;

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.buttom);
        //这是动画的添加,表示向右移动了300像素,但是这个buttom的点击事件还是在原地
        //customView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 这是点击事件");
                //这里就可以证明这个layout方法是把bottom本身的属性一起变更的,不是只是动画变更了
            }
        });
    }
}