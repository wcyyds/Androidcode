package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<girl> girlList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initgirl();

    }

    private void initgirl(){
        for(int i = 0 ; i < 5; i++){
            girl yi = new girl("yi",R.drawable.yi);
            girlList.add(yi);
            girl er = new girl("er",R.drawable.er);
            girlList.add(er);
            girl san = new girl("san",R.drawable.san);
            girlList.add(san);
            girl si = new girl("si",R.drawable.si);
            girlList.add(yi);
            girl wu = new girl("wu",R.drawable.wu);
            girlList.add(wu);

        }
    }

}