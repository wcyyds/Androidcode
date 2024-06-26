package com.example.dispatcheventlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private HorizontalScrollViewEx mListContainer;
    private DisplayMetrics displayMetrics;//用来获得屏幕尺寸的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        initView();
    }

    private void initView(){//初始化视图
        displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = getLayoutInflater();//获取xml加载器--加载到该Activity中
        mListContainer = findViewById(R.id.mListContainer);
        int screenWidth = 0;//屏幕宽度
        int screenHeight = 0;//屏幕高度
        if(windowManager != null){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenWidth = displayMetrics.widthPixels;
            screenHeight = displayMetrics.heightPixels;
        }
        Log.d(TAG, "width : "+screenWidth);
        Log.d(TAG, "height : "+screenHeight);
        for(int i = 0;i < 3;i++){
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout,mListContainer,false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.mTitle);
            textView.setText("Page "+(i + 1));
            layout.setBackgroundColor(Color.rgb(255/(i+1),255/(i+1),255/(i+1)));//设置背景色
            createList(layout);//为当前页面生成纵向List
            mListContainer.addView(layout);//添加到自定义View中
        }
        mListContainer.setInfo(3,screenWidth);
    }

    private void createList(ViewGroup layout){
        ListView listView = layout.findViewById(R.id.mList);
        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0; i < 50;i++){
            datas.add("name "+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
    }
}
