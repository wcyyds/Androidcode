package com.example.listview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initFruits(){
        for(int i = 0; i < 3; i++){
            Fruit chutian = new Fruit("雏田",R.drawable.chutian);
            fruitList.add(chutian);

            Fruit guhe = new Fruit("古河",R.drawable.guhe);
            fruitList.add(guhe);
            Fruit huangmao = new Fruit("黄毛",R.drawable.huangmao);
            fruitList.add(huangmao);
            Fruit leimu = new Fruit("蕾姆",R.drawable.leimu);
            fruitList.add(leimu);
            Fruit mayi = new Fruit("麻衣",R.drawable.mayi);
            fruitList.add(mayi);
            Fruit nvdi = new Fruit("女帝",R.drawable.nvdi);
            fruitList.add(nvdi);
            Fruit paojie = new Fruit("炮姐",R.drawable.paojie);
            fruitList.add(paojie);
            Fruit wang = new Fruit("吾王",R.drawable.wang);
            fruitList.add(wang);
            Fruit xiaolan = new Fruit("小兰",R.drawable.xiaolan);
            fruitList.add(xiaolan);
            Fruit xuexiaoban = new Fruit("血小板",R.drawable.xuexiaoban);
            fruitList.add(xuexiaoban);
            Fruit yasina = new Fruit("亚丝娜",R.drawable.yasina);
            fruitList.add(yasina);
        }
    }
//
//
//    private String[] data = {"Apple","Banana","Orange","Watermelon","Pear",
//            "Grape","Pineapple","Strawberry","Cherry","Mango",
//            "Apple","Banana","Orange","Watermelon","Pear",
//            "Grape","Pineapple","Strawberry","Cherry","Mango"};
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//    }
}
