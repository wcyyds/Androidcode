package com.example.viewpager_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPage();
        viewPager2 = findViewById(R.id.myViewPager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),
                getLifecycle(),fragments);
        viewPager2.setAdapter(myAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Log.d(TAG, "onConfigureTab: 看看这个能有几次的TabLayout");
                tab.setText(tablayoutdata.get(position));
            }
        }).attach();

    }

    private void initPage() {
        fragments.add(BlankFragment.newInstance("我最帅","1"));
        fragments.add(BlankFragment.newInstance("我最丑","2"));
        fragments.add(BlankFragment.newInstance("我很帅","3"));
        fragments.add(BlankFragment.newInstance("我很丑","4"));

        tablayoutdata.add("1");
        tablayoutdata.add("2");
        tablayoutdata.add("3");
        tablayoutdata.add("4");
    }
}
