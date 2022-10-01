package com.example.recyclerview2;

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
        Log.d(TAG, "onCreate: ");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Log.d(TAG, "onCreate: jiazaibuju");
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        Log.d(TAG, "onCreate: jiazaishipeiqi");
        recyclerView.setAdapter(fruitAdapter);
    }


    private void initFruits(){
        Log.d(TAG, "initFruits: ");
        for(int i = 0; i < 3; i++){
            Fruit chutian = new Fruit("chutian",R.drawable.chutian);
            fruitList.add(chutian);
            Fruit guhe = new Fruit("guhe",R.drawable.guhe);
            fruitList.add(guhe);
            Fruit huangmao = new Fruit("huangmao",R.drawable.huangmao);
            fruitList.add(huangmao);
            Fruit leimu = new Fruit("leimu",R.drawable.leimu);
            fruitList.add(leimu);
            Fruit mayi = new Fruit("mayi",R.drawable.mayi);
            fruitList.add(mayi);
            Fruit nvdi = new Fruit("nvdi",R.drawable.nvdi);
            fruitList.add(nvdi);
            Fruit paojie = new Fruit("paojie",R.drawable.paojie);
            fruitList.add(paojie);
            Fruit wang = new Fruit("wang",R.drawable.wang);
            fruitList.add(wang);
            Fruit xiaolan = new Fruit("xiaolan",R.drawable.xiaolan);
            fruitList.add(xiaolan);
            Fruit xuexiaoban = new Fruit("xuexiaoban",R.drawable.xuexiaoban);
            fruitList.add(xuexiaoban);
            Fruit yasina = new Fruit("yasina",R.drawable.yasina);
            fruitList.add(yasina);
        }
    }
}