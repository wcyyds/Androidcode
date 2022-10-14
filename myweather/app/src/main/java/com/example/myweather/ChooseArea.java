package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ChooseArea extends AppCompatActivity {

    private Button back_main;

    private Button search_city;

    private List<choose> chooseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);

        //初始化各种控件
        back_main = (Button) findViewById(R.id.back_main);


        initchoose();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.choose_city);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chooseAdapter adapter = new chooseAdapter(chooseList);
        recyclerView.setAdapter(adapter);
    }

    private void initchoose(){
        for(int i = 0; i < 4; i++){
            choose choose1 = new choose("鄠邑区","严重污染", "15℃", "26℃", "10℃", R.drawable.wu);
            chooseList.add(choose1);
            choose choose2 = new choose("长安区","严重污染严重污染", "15℃", "26℃", "10℃", R.drawable.qing);
            chooseList.add(choose2);
            choose choose3 = new choose("蓝天县","优", "15℃", "26℃", "10℃", R.drawable.lei);
            chooseList.add(choose1);
            choose choose4 = new choose("阎良区","良好", "15℃", "26℃", "10℃", R.drawable.yin);
            chooseList.add(choose4);
        }
    }

}