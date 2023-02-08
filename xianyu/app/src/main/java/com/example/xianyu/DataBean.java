package com.example.xianyu;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBean {

    private int imageRes;

    public DataBean(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public static List<DataBean> getTestData1(){

        Log.d("1", "getTestData1: 这里是初始化了链表");
        List<DataBean> mDatas = new ArrayList<>();
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        DataBean one = new DataBean(R.drawable.fruit_2);
        mDatas.add(one);
        DataBean two = new DataBean(R.drawable.fruit_12);
        mDatas.add(two);
        DataBean three = new DataBean(R.drawable.fruit_7);
        mDatas.add(three);
        return mDatas;
    }

}
