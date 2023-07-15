package com.example.databindinglearntest04java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.databindinglearntest04java.databinding.ActivityMain2Binding;
import com.example.databindinglearntest04java.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding2 = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        Swordsman swordsman = new Swordsman("zhang", "s");
        binding2.setSwordsman(swordsman);
        binding2.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swordsman.setLevel("2");
            }
        });
    }
}