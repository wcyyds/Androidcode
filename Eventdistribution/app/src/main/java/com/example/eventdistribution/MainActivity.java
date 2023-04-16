package com.example.eventdistribution;

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
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewPagerList);
        viewPager2.setAdapter(viewPagerAdapter);

    }

    private void initData() {
        ViewPager viewPager1 = new ViewPager(R.drawable.ic_launcher_background);
        viewPagerList.add(viewPager1);
        ViewPager viewPager2 = new ViewPager(R.drawable.ic_launcher_foreground);
        viewPagerList.add(viewPager2);
        ViewPager viewPager3 = new ViewPager(android.R.drawable.btn_star_big_on);
        viewPagerList.add(viewPager3);
    }
}