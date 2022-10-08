package com.example.recycleview30;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }


    private void initFruits(){
        Log.d(TAG, "initFruits: ");
        for(int i = 0; i < 3; i++){
            Fruit apple = new Fruit("WANGCHENYANG",R.drawable.apple);
            fruitList.add(apple);
            Fruit beer = new Fruit("beer",R.drawable.beer);
            fruitList.add(beer);
            Fruit bread = new Fruit("bread",R.drawable.bread);
            fruitList.add(bread);
            Fruit burger = new Fruit("burger",R.drawable.burger);
            fruitList.add(burger);
            Fruit cake = new Fruit("cake",R.drawable.cake);
            fruitList.add(cake);
            Fruit cheesecake = new Fruit("cheesecake",R.drawable.cheesecake);
            fruitList.add(cheesecake);
            Fruit cherry = new Fruit("cherry",R.drawable.cherry);
            fruitList.add(cherry);
            Fruit chips = new Fruit("chips",R.drawable.chips);
            fruitList.add(chips);
            Fruit kiwi = new Fruit("kiwi",R.drawable.kiwi);
            fruitList.add(kiwi);
            Fruit lemon = new Fruit("lemon",R.drawable.lemon);
            fruitList.add(lemon);
            Fruit pear = new Fruit("pear",R.drawable.pear);
            fruitList.add(pear);
            Fruit pineapple = new Fruit("pineapple",R.drawable.pineapple);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("strawberry",R.drawable.strawberry);
            fruitList.add(strawberry);
            Fruit tea = new Fruit("tea",R.drawable.tea);
            fruitList.add(tea);
            Fruit watermelon = new Fruit("watermelon",R.drawable.watermelon);
            fruitList.add(watermelon);
        }
    }
}