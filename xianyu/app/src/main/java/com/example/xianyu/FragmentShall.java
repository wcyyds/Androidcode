package com.example.xianyu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.xianyu.homepage.viewpager2_1_1;
import com.example.xianyu.homepage.viewpager2_1_2;
import com.example.xianyu.homepage.viewpager2_1_3;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentShall#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentShall extends Fragment {


    ViewPager2 viewPager2_shall;
    TabLayout tabLayout_shall;

    private List<Fragment> fragments_shall = new ArrayList<>();
    private List<String> tablayoutdata_shall = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentShall() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentShall.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentShall newInstance(String param1, String param2) {
        Log.d("FragmentShall", "newInstance: 这里使用了一个newInstance的方法使碎片重新建立的时候保存原来的数据");
        FragmentShall fragment = new FragmentShall();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate: 这里是oncreate创建了碎片");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initPage_shall(){
        fragments_shall.add(tuijianFragment.newInstance("111","111"));
        fragments_shall.add(faxianFragment.newInstance("1111","1111"));

        tablayoutdata_shall.add("推荐");
        tablayoutdata_shall.add("发现");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "onCreateView: 这里是onCreateView创建一个碎片的视图");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPage_shall();
        viewPager2_shall = view.findViewById(R.id.shall_viewpager);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),getLifecycle(),fragments_shall);
        viewPager2_shall.setAdapter(myAdapter);

        tabLayout_shall = view.findViewById(R.id.shall_tablayout);
        tabLayout_shall.setSelectedTabIndicatorHeight(0);
        new TabLayoutMediator(tabLayout_shall, viewPager2_shall, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tablayoutdata_shall.get(position));
            }
        }).attach();

    }
}