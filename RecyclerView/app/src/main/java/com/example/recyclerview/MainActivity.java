package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new com.example.recyclerview.FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){
        for(int i = 0; i < 3; i++){
            Fruit chutian = new Fruit(getRandomlengthName("chutian"),R.drawable.chutian);
            fruitList.add(chutian);
            Fruit guhe = new Fruit(getRandomlengthName("guhe"),R.drawable.guhe);
            fruitList.add(guhe);
            Fruit huangmao = new Fruit(getRandomlengthName("huangmao"),R.drawable.huangmao);
            fruitList.add(huangmao);
            Fruit leimu = new Fruit(getRandomlengthName("leimu"),R.drawable.leimu);
            fruitList.add(leimu);
            Fruit mayi = new Fruit(getRandomlengthName("mayi"),R.drawable.mayi);
            fruitList.add(mayi);
            Fruit nvdi = new Fruit(getRandomlengthName("nvdi"),R.drawable.nvdi);
            fruitList.add(nvdi);
            Fruit paojie = new Fruit(getRandomlengthName("paojie"),R.drawable.paojie);
            fruitList.add(paojie);
            Fruit wang = new Fruit(getRandomlengthName("wang"),R.drawable.wang);
            fruitList.add(wang);
            Fruit xiaolan = new Fruit(getRandomlengthName("xiaolan"),R.drawable.xiaolan);
            fruitList.add(xiaolan);
            Fruit xuexiaoban = new Fruit(getRandomlengthName("xuexiaoban"),R.drawable.xuexiaoban);
            fruitList.add(xuexiaoban);
            Fruit yasina = new Fruit(getRandomlengthName("yasina"),R.drawable.yasina);
            fruitList.add(yasina);
        }
    }
    private String getRandomlengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuffer builder = new StringBuffer();
        for(int i = 0; i < length; i++){
            builder.append(name);
        }
        return  builder.toString();
    }
}