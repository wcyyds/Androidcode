package com.example.cameraalbumtest;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Context context;

    private static final String TAG = "MainActivity";

    private Banner banner;


    private List<Integer> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void getTestData1(){
        banner = (Banner) findViewById(R.id.banner);

        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        mDatas.add(R.drawable.fruit_2);
        mDatas.add(R.drawable.fruit_12);
        mDatas.add(R.drawable.fruit_7);

        Log.d(TAG, "getTestData1: ");
        banner.setAdapter(new ImageAdapter(context, DataBean.getTestData1()))
                .start();

    }


    private Banner mBanner;
    private List<DataBean> mList = new ArrayList<>();

    private void initView(){
        mBanner = (Banner) findViewById(R.id.banner);
        mBanner.setAdapter(new ImageAdapter(MainActivity.this, mList));

        // Set Banner is auto to loop.
        mBanner.isAutoLoop(true);

        // Set an indicator for Banner.
        mBanner.setIndicator(new CircleIndicator(this));
        mBanner.start();
    }

    private void initData(){
        mList.add(new DataBean(R.drawable.fruit_2));
        mList.add(new DataBean(R.drawable.fruit_12));
        mList.add(new DataBean(R.drawable.fruit_7));
    }



}