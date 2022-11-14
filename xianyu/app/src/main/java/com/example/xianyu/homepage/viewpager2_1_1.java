package com.example.xianyu.homepage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xianyu.R;
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

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tablayoutdata = new ArrayList<>();

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
        initPage();
        viewPager2 = view.findViewById(R.id.myViewPager);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),
                getLifecycle(),fragments);
        viewPager2.setAdapter(myAdapter);

        tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tablayoutdata.get(position));
            }
        }).attach();
    }



    private void initPage() {
        fragments.add(viewpager2_1_1_many.newInstance("我最帅","1"));
        fragments.add(viewpager2_1_1_many.newInstance("我最丑","2"));
        fragments.add(viewpager2_1_1_many.newInstance("我很帅","3"));

        tablayoutdata.add("1");
        tablayoutdata.add("2");
        tablayoutdata.add("3");
    }

}