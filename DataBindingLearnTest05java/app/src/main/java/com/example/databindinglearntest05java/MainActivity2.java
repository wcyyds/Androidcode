package com.example.databindinglearntest05java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.databindinglearntest05java.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding2 = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        ObSwordsman obSwordsman = new ObSwordsman("hu0205","0205");
        binding2.setObswordsman(obSwordsman);
        binding2.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman.setLevel("010101010");
                Log.d("111","111" + obSwordsman.getLevel());
            }
        });
    }
}