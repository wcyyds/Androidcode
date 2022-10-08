package com.example.weather;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<timehour> hourtimeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBar statusBar = new StatusBar(MainActivity.this);
        statusBar.setStatusBarColor(R.color.transparent);//设置为透明
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
//        inittimehour();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        hourAdapter adapter = new hourAdapter(hourtimeList);
//        recyclerView.setAdapter(adapter);
    }

    private void inittimehour(){
        for (int i = 0;i < 3; i++){
            timehour a = new timehour("15:00", "9℃",R.drawable.icon_rain,"优1");
            hourtimeList.add(a);
            timehour b = new timehour("16:00", "10℃",R.drawable.icon_rain,"优2");
            hourtimeList.add(b);
            timehour c = new timehour("15:00", "11℃",R.drawable.icon_rain,"优3");
            hourtimeList.add(c);
            timehour d = new timehour("15:00", "12℃",R.drawable.icon_rain,"优4");
            hourtimeList.add(d);
            timehour e = new timehour("15:00", "13℃",R.drawable.icon_rain,"优5");
            hourtimeList.add(e);
            timehour f = new timehour("15:00", "14℃",R.drawable.icon_rain,"优6");
            hourtimeList.add(f);
            timehour h = new timehour("15:00", "15℃",R.drawable.icon_rain,"优7");
            hourtimeList.add(h);
        }
    }
}