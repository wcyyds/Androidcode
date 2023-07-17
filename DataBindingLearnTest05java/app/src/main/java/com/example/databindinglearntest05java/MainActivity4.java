package com.example.databindinglearntest05java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.databindinglearntest05java.databinding.ActivityMain4Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding4 = DataBindingUtil.setContentView(this, R.layout.activity_main4);
        initRecyclerView();

    }
    private void initRecyclerView(){
        LinearLayoutManager manager =  new LinearLayoutManager(MainActivity4.this);
        binding4.recycler.setLayoutManager(manager);
        SwordsmanAdapter swordsmanAdapter = new SwordsmanAdapter(getList());
        binding4.recycler.setAdapter(swordsmanAdapter);
    }

    private List<Swordsman> getList(){
        List<Swordsman> swordsmanList = new ArrayList<>();
        Swordsman swordsman = new Swordsman("www", "111");
        Swordsman swordsman1 = new Swordsman("eee", "222");
        swordsmanList.add(swordsman1);
        swordsmanList.add(swordsman);
        return swordsmanList;
    }
}