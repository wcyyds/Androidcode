package com.example.constraintlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //将上面的视图隐藏,不好看
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null){
                actionBar.hide();
            }

            //现在是找到了现在的折叠的东西,1111是展开,2222是折叠,3333是其他(就是在中间的到时候)
           appBarLayout =  findViewById(R.id.app_bar);
            appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeedListener() {
                @Override
                public void onStateChanged(AppBarLayout appBarLayout, State state) {
                    // 展开状态
                    if (state == State.EXPANDED) {
                        Log.d(TAG, "onStateChanged: 现在是11111");
                    } else if (state == State.COLLAPSED) {
                        Log.d(TAG, "onStateChanged: 现在是22222");
                    } else {
                        Log.d(TAG, "onStateChanged: 现在是33333");
                    }
                }
            });


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
        fragments.add(viewpager2_1.newInstance("我最帅","1"));
        fragments.add(viewpager2_2.newInstance("我最丑","2"));
        fragments.add(viewpager2_3.newInstance("我很帅","3"));

        tablayoutdata.add("1");
        tablayoutdata.add("2");
        tablayoutdata.add("3");
    }

}