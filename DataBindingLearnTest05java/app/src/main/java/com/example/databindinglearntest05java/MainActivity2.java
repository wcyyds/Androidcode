package com.example.databindinglearntest05java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.databindinglearntest05java.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding2 = DataBindingUtil.setContentView(this, R.layout.activity_main2);
//        ObSwordsman obSwordsman = new ObSwordsman("hu0205","0205");
//        binding2.setObswordsman(obSwordsman);
//        binding2.btNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                obSwordsman.setLevel("010101010");
//                Log.d("111","111" + obSwordsman.getLevel());
//            }
//        });

//        ObservableArrayList arrayList = new ObservableArrayList<>();
//        Swordsman swordsman = new Swordsman("张无忌", "S");
//        Swordsman swordsman1 = new Swordsman("周置若", "D");
//        arrayList.add(swordsman1);
//        arrayList.add(swordsman);
//        binding2.setList(arrayList);
//        binding2.btNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                swordsman1.setName("123");
//                swordsman1.setName("456");
//                arrayList.add(swordsman1);
//            }
//        });
        ObSwordsman swordsman = new ObSwordsman("我是谁", "123");
        binding2.setObswordsman(swordsman);
        binding2.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swordsman.setName("woshi");
            }
        });
    }
}