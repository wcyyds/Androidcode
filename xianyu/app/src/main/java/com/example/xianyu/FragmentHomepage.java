package com.example.xianyu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xianyu.homepage.MyAdapter;
import com.example.xianyu.homepage.viewpager2_1_1;
import com.example.xianyu.homepage.viewpager2_1_2;
import com.example.xianyu.homepage.viewpager2_1_3;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHomepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHomepage extends Fragment {


    ViewPager2 viewPager2;
    TabLayout tabLayout;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHomepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHomepage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHomepage newInstance(String param1, String param2) {
        FragmentHomepage fragment = new FragmentHomepage();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    private void initPage(){
        fragments.add(viewpager2_1_1.newInstance("我很帅","1"));
        fragments.add(viewpager2_1_2.newInstance("我很帅","1"));
        fragments.add(viewpager2_1_3.newInstance("我很帅","1"));

        tablayoutdata.add("关注");
        tablayoutdata.add("推荐");
        tablayoutdata.add("西安");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPage();
        viewPager2 = view.findViewById(R.id.myviewpager);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),getLifecycle(),fragments);
        viewPager2.setAdapter(myAdapter);
        //这里禁止了外部view pager的滑动
        viewPager2.setUserInputEnabled(false);
        //如果能在这里进行折叠监听就好了,当折叠没有完毕时,
        // 不禁止外部viewpage的滑动,如果折叠完全则禁止外部的viewpage的滑动,进行内部的viewpage的滑动

        tabLayout = view.findViewById(R.id.tablayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tablayoutdata.get(position));
            }
        }).attach();
    }

}