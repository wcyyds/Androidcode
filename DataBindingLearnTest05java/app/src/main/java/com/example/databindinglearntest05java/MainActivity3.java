package com.example.databindinglearntest05java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.databindinglearntest05java.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain3Binding binding3 = DataBindingUtil.
                setContentView(this, R.layout.activity_main3);
        ObFSwordsman obFSwordsman = new ObFSwordsman("0503","0503111");
        binding3.setObfswordsman(obFSwordsman);
        binding3.bt3Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obFSwordsman.level.set("wokaozhe");
            }
        });



    }
}