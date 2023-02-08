package com.example.xianyu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;


     private BottomNavigationView bottomNavigationView;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //透明状态栏
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main);

        //将上面的视图隐藏,不好看
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);


        //获取navController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //通过setupWithNavController将底部导航栏和导航控制器进行绑定
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        initViewPager();
//        viewPager2 = findViewById(R.id.myViewPager);
//        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),
//                getLifecycle(),fragments);
//        viewPager2.setAdapter(myAdapter);
//
//        tabLayout = findViewById(R.id.tabLayout);
//        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(tablayoutdata.get(position));
//            }
//        }).attach();

    }

//    private void initViewPager(){
//        fragments.add(viewpager2_1_1.newInstance("我最帅","1"));
//        fragments.add(viewpager2_1_2.newInstance("我最丑","2"));
//        fragments.add(viewpager2_1_3.newInstance("我很帅","3"));
//
//        tablayoutdata.add("1");
//        tablayoutdata.add("2");
//        tablayoutdata.add("3");
//    }

}