package com.example.view_pager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ViewPager> viewPagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        HorizontalVpAdapter horizontalVpAdapter = new HorizontalVpAdapter(viewPagerList);
        viewPager2.setAdapter(horizontalVpAdapter);
    }

    private void initData(){
        for (int i = 0; i < 2; i++){
            ViewPager viewPager1 = new ViewPager("123", R.drawable.cloudy);
            viewPagerList.add(viewPager1);
            ViewPager viewPager2 = new ViewPager("456", R.drawable.moon);
            viewPagerList.add(viewPager2);
            ViewPager viewPager3 = new ViewPager("789", R.drawable.sunny);
            viewPagerList.add(viewPager3);
        }
    }

}