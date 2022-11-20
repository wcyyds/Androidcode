package com.example.xianyu.homepage;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xianyu.AppBarStateChangeedListener;
import com.example.xianyu.FruitAdapter;
import com.example.xianyu.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewpager2_1_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewpager2_1_1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String mTextString1 = "xxx";
    String mTextString2 = "xxx";
    View rootView;

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    ViewPager2 Pargent_viewPager2;
    RecyclerView recyclerView1;
    viewpager2_1_1_many viewpager2_1_1_many;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();
    private List<MiddleChoiceSell> middleChoiceSellList = new ArrayList<>();

    public static viewpager2_1_1 newInstance(String param1, String param2) {

        viewpager2_1_1 fragment = new viewpager2_1_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString1 = getArguments().getString(ARG_PARAM1);
            mTextString2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_viewpager2_1_1, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //现在试试能不能在这里控制上一个viewpage的滑动禁止和不禁止

        Pargent_viewPager2 = (ViewPager2)getActivity().findViewById(R.id.myviewpager);


        //现在是找到了现在的折叠的东西,1111是展开,2222是折叠,3333是其他(就是在中间的到时候)
        appBarLayout =  view.findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                // 展开状态
                if (state == State.EXPANDED) {
                    Log.d("111", "onStateChanged: 现在是11111");
                    Pargent_viewPager2.setUserInputEnabled(true);
                    //recyclerView1.setNestedScrollingEnabled(false);

                } else if (state == State.COLLAPSED) {
                    Pargent_viewPager2.setUserInputEnabled(false);
                    Log.d("222", "onStateChanged: 现在是22222");
                    //recyclerView1.setNestedScrollingEnabled(true);
                } else {
                    Log.d("333", "onStateChanged: 现在是33333");
                    Pargent_viewPager2.setUserInputEnabled(true);
                    //recyclerView1.setNestedScrollingEnabled(false);
                }
            }
        });

        initPage();
        viewPager2 = view.findViewById(R.id.myViewPager1);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),
                getLifecycle(),fragments);
        viewPager2.setAdapter(myAdapter);

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorHeight(0);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tablayoutdata.get(position));
            }
        }).attach();

        initMiddleChoiceSells();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_viewpager2_2_1_collapsing_recycleview);
        LinearLayoutManager layoutManager12 = new LinearLayoutManager(getContext());
        layoutManager12.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager12);
        MiddleChoiceSellAdapter middleChoiceSellAdapter = new MiddleChoiceSellAdapter(middleChoiceSellList);
        recyclerView.setAdapter(middleChoiceSellAdapter);

    }

    private void initMiddleChoiceSells(){
        MiddleChoiceSell middleChoiceSell1 = new MiddleChoiceSell("二手车","专业检测",R.drawable.homepage_third_one);
        middleChoiceSellList.add(middleChoiceSell1);
        MiddleChoiceSell middleChoiceSell2 = new MiddleChoiceSell("手机帮卖","2天必卖",R.drawable.homepage_third_two);
        middleChoiceSellList.add(middleChoiceSell2);
        MiddleChoiceSell middleChoiceSell3 = new MiddleChoiceSell("校园圈","学生专属",R.drawable.homepage_third_three);
        middleChoiceSellList.add(middleChoiceSell3);
    }



    private void initPage() {
        fragments.add(viewpager2_1_1_many.newInstance("我最帅","1"));
        fragments.add(viewpager2_1_1_many.newInstance("我最丑","2"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));


        tablayoutdata.add("猜你喜欢");
        tablayoutdata.add("最新发布");
        tablayoutdata.add("鱼币抵钱");
        tablayoutdata.add("游乐设施");
        tablayoutdata.add("网球");
        tablayoutdata.add("架子鼓");
        tablayoutdata.add("今日降价");
        tablayoutdata.add("尤克里里");
        tablayoutdata.add("潜水");
        tablayoutdata.add("演艺演出");

    }

}