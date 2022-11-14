package com.example.constraintlayout;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends FragmentStateAdapter {

    private static final String TAG = "MyAdapter";
    List<Fragment> fragments = new ArrayList<>();

    public MyAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        Log.d(TAG, "MyAdapter: 这是那个适配器的构造函数");
        this.fragments = fragments;
        Log.d(TAG, "MyAdapter: ");
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG, "createFragment: 看看这是第几个视图" + position);
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}

