package com.example.xianyu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        fragments.add(viewpager2_1_2.newInstance("?????????","1"));
        fragments.add(viewpager2_1_1.newInstance("?????????","1"));
        fragments.add(viewpager2_1_3.newInstance("?????????","1"));

        tablayoutdata.add("??????");
        tablayoutdata.add("??????");
        tablayoutdata.add("??????");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("?????????????????????", "onViewCreated: ?????????????????????????????????onviewcreated");
        super.onViewCreated(view, savedInstanceState);
        initPage();
        viewPager2 = view.findViewById(R.id.myviewpager);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),getLifecycle(),fragments);
        viewPager2.setAdapter(myAdapter);
        //????????????????????????????????????
        Log.d("1", "onViewCreated: ????????????1?????????????????????");
        viewPager2.setCurrentItem(2);



        tabLayout = view.findViewById(R.id.tablayout_homepage);
        tabLayout.setSelectedTabIndicatorHeight(0);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tablayoutdata.get(position));
            }
        }).attach();

        //????????????????????????tablayout?????????,tablayout??????????????????????????????????????????TextView???,?????????????????????????????????
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            //?????????????????????
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("1111", "onTabSelected: 1111" + tab.toString());
                View view1 = tab.getCustomView();
                if(null != view1 && view1 instanceof TextView){
                    ((TextView) view1).setTextSize(22);
                    ((TextView) view1).setTextColor(ContextCompat.
                            getColor(getContext(), R.color.tablayout_select));
                }
            }

            //????????????????????????
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("2222", "onTabSelected: 2222" + tab.toString());
                View view2 = tab.getCustomView();
                if(null != view2 && view2 instanceof TextView){
                    ((TextView) view2).setTextSize(22);
                    ((TextView) view2).setTextColor(ContextCompat.
                            getColor(getContext(), R.color.tablayout_select));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}