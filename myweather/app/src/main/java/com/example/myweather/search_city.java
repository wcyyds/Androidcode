package com.example.myweather;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myweather.net.Search;
import com.example.myweather.net.Search_Location;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class search_city extends AppCompatActivity implements View.OnClickListener{

    private MyDatabaseHelper dbHelper;

    private EditText search_text;

    private Button back_2;

    //这是一个热门城市的linearlayout,在输入的时候要进行隐藏
    private LinearLayout hot_city;
    private LinearLayout linearLayout_list_view;

    private ListView listView;
    private List<String>  arr = new ArrayList<>();
    private List<String> arr1 = new ArrayList<>();
    private List<String> arr2 = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private String now1;
    private String now2;
    //private List<String> data = new ArrayList<>();

    private Intent intent_choose = new Intent();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        //初始化实例
        search_text = (EditText) findViewById(R.id.search_edit);
        hot_city = (LinearLayout) findViewById(R.id.hot_city);
        linearLayout_list_view = (LinearLayout) findViewById(R.id.layout_listview);
        linearLayout_list_view.setVisibility(View.GONE);

        back_2 = (Button) findViewById(R.id.back_choosearea);
        back_2.setOnClickListener(this);

        Button dingwei = (Button) findViewById(R.id.dingwei);
        dingwei.setOnClickListener(this);
        Button beijing = (Button) findViewById(R.id.beijing);
        beijing.setOnClickListener(this);
        Button shanghai = (Button) findViewById(R.id.shanghai);
        shanghai.setOnClickListener(this);

        Button guangzhou = (Button) findViewById(R.id.guangzhou);
        guangzhou.setOnClickListener(this);
        Button shenzhen = (Button) findViewById(R.id.shenzhen);
        shenzhen.setOnClickListener(this);
        Button zhuhai = (Button) findViewById(R.id.zhuhai);
        zhuhai.setOnClickListener(this);

        Button foshan = (Button) findViewById(R.id.foshan);
        foshan.setOnClickListener(this);
        Button nanjing = (Button) findViewById(R.id.nanjing);
        nanjing.setOnClickListener(this);
        Button suzhou = (Button) findViewById(R.id.suzhou);
        suzhou.setOnClickListener(this);

        Button xiamen = (Button) findViewById(R.id.xiamen);
        xiamen.setOnClickListener(this);
        Button nanning = (Button) findViewById(R.id.nanning);
        nanning.setOnClickListener(this);
        Button kunming = (Button) findViewById(R.id.kunming);
        kunming.setOnClickListener(this);

        Button chengdu = (Button) findViewById(R.id.chengdu);
        chengdu.setOnClickListener(this);
        Button changsha = (Button) findViewById(R.id.changsha);
        changsha.setOnClickListener(this);
        Button fuzhou = (Button) findViewById(R.id.fuzhou);
        fuzhou.setOnClickListener(this);

        Button hangzhou = (Button) findViewById(R.id.hangzhou);
        hangzhou.setOnClickListener(this);
        Button wuhan = (Button) findViewById(R.id.wuhan);
        wuhan.setOnClickListener(this);
        Button qingdao = (Button) findViewById(R.id.qingdao);
        qingdao.setOnClickListener(this);

        Button xian = (Button) findViewById(R.id.xian);
        xian.setOnClickListener(this);
        Button taiyuan = (Button) findViewById(R.id.taiyuan);
        taiyuan.setOnClickListener(this);
        Button shijiazhuang = (Button) findViewById(R.id.shijiazhuang);
        shijiazhuang.setOnClickListener(this);

        Button shenyang = (Button) findViewById(R.id.shenyang);
        shenyang.setOnClickListener(this);
        Button chongqing = (Button) findViewById(R.id.chongqing);
        chongqing.setOnClickListener(this);
        Button tianjin = (Button) findViewById(R.id.tianjin);
        tianjin.setOnClickListener(this);




        dbHelper = new MyDatabaseHelper(this, " BookStore.db", null, 1);


        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(
                search_city.this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                now1 = arr1.get(i);
                now2 = arr2.get(i);
                Log.d(TAG, "onItemClick: 这是listview的点击事件");
                //这里就是把获得到的城市id,弄到choose里面然后
                //点击就是添加,这个就只能搜索,然后添加到本地内存上面去,然后返回到上一个活动上面,上一个可以点击很多东西,然后点击之后就返回去
                //然后把本地存储的东西读取出来,记得还要查询一下
                //然后返回到主的函数里面去
                //data.add(now1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Boolean chachong = false;
                //先查询所有的数据要查重
                Log.d(TAG, "onItemClick: 看看arr1是什么" + arr1);
                Cursor cursor = db.query("Book", null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    Log.d(TAG, "onItemClick: 进入大的if判断");
                    do {
                        Log.d(TAG, "onItemClick: 进入dowhile循环");
                        @SuppressLint("Range") String SQL_cityId = cursor.getString(cursor.getColumnIndex("cityid"));
                        Log.d(TAG, "onItemClick: 查看查询到的数据" + SQL_cityId);
                        if(now1.equals(SQL_cityId)){
                            chachong = true;
                            Log.d(TAG, "onItemClick: 看看有没有进入这个查询所有的数据中去");
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();


                Log.d(TAG, "onItemClick: 来到了if判读语句当中");
                if(chachong){
                    Toast.makeText(search_city.this, "你已经添加过了", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values = new ContentValues();
                    //开始添加数据1
                    values.put("cityid", now1);
                    values.put("city", now2);
                    db.insert("Book", null, values);
                    Toast.makeText(search_city.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        //对EditText进行进行实时监听
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //这个是对输入中的实时监听,第一个参数是实时的输入的字符串
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "onTextChanged: " + charSequence);
                Log.d(TAG, "onTextChanged: " + i);
                Log.d(TAG, "onTextChanged: " + i1);
                Log.d(TAG, "onTextChanged: " + i2);
                if(charSequence.length()!=0){
                    Log.d(TAG, "if_limian");
                    //将热门城市进行隐藏,且不保留位置
                    hot_city.setVisibility(View.GONE);
                    //将listview进行显示
                    Log.d(TAG, "onTextChanged: 将listview进行显示111");
                    linearLayout_list_view.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onTextChanged: 将listview进行显示222");
                    requestdayweather(charSequence.toString());
                }else {
                    linearLayout_list_view.setVisibility(View.GONE);
                    Log.d(TAG, "onTextChanged: 将hot_city进行显示");
                    hot_city.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    /**
     * 根据请求的网络IP,接口自动返回当前IP的今天的天气情况
     */
    public void requestdayweather(String search){
        Log.d(TAG, "requestdayweather: 进入到了requestdayweather");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 开始跑到run里面了");
                //使用的是httpURL
                //这个使用的是URLhttp,不知道为什么OKhttp用不了
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://geoapi.heweather.net/v2/city/lookup?location=" + search + "&key=b92646e0f4194731b50870798cfad1d0");
                    connection = (HttpURLConnection)  url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    Log.d(TAG, "run: 开始读取了");
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    Log.d(TAG, "run: " + response.toString());
                    Log.d(TAG, "run: 跑完了");
                    Gson gson = new Gson();
                    Search search1= gson.fromJson(response.toString() , Search.class);


                    if(!search1.zhuangtai.equals("200")){
                        arr.clear();
                        arr1.clear();
                        arr2.clear();
                        //不进行隐藏,但是把listview进行刷新
                        showlistview();
                        Log.d(TAG, "run: 进入到了200里面");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(search_city.this, "你的输入出现问题,请重新输入",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
//                        for (Search_Location search_location1 : search1.search_cities){
//                            Log.d(TAG, "111: " + search_location1.city_name);
//                            Log.d(TAG, "222: " + search_location1.city_id);
//                            Log.d(TAG, "333: " + search_location1.shi);
//                            Log.d(TAG, "444: " + search_location1.sheng);
//                            Log.d(TAG, "555: " + search_location1.guojia);
//                        }

                        Log.d(TAG, "run: 开始给data里面添加数据");
                        arr.clear();
                        arr1.clear();
                        arr2.clear();;
                        for(Search_Location search_location2 : search1.search_cities){
                            arr.add(search_location2.city_name + "-" + search_location2.shi +
                                    "-" + search_location2.sheng + "-" + search_location2.guojia);
                            arr1.add(search_location2.city_id);
                            arr2.add(search_location2.city_name);
                        }

                        showlistview();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showlistview(){
        Log.d(TAG, "showlistview: 加载listview");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_choosearea:
                finish();
                break;
            case R.id.dingwei:
                intent_choose.putExtra("date_return", "");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.beijing:
                intent_choose.putExtra("date_return", "101010100");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.shanghai:
                intent_choose.putExtra("date_return", "");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.guangzhou:
                intent_choose.putExtra("date_return", "101280101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.shenzhen:
                intent_choose.putExtra("date_return", "101280601");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.zhuhai:
                intent_choose.putExtra("date_return", "101280701");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.foshan:
                intent_choose.putExtra("date_return", "101280800");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.nanjing:
                intent_choose.putExtra("date_return", "101190101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.suzhou:
                intent_choose.putExtra("date_return", "101190401");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.xiamen:
                intent_choose.putExtra("date_return", "101230201");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.nanning:
                intent_choose.putExtra("date_return", "101300101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.kunming:
                intent_choose.putExtra("date_return", "101290101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.chengdu:
                intent_choose.putExtra("date_return", "101270101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.changsha:
                intent_choose.putExtra("date_return", "101250101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.fuzhou:
                intent_choose.putExtra("date_return", "101230101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.hangzhou:
                intent_choose.putExtra("date_return", "101210101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.wuhan:
                intent_choose.putExtra("date_return", "101200101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.qingdao:
                intent_choose.putExtra("date_return", "101120201");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.xian:
                intent_choose.putExtra("date_return", "101110101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.taiyuan:
                intent_choose.putExtra("date_return", "101100101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.shijiazhuang:
                intent_choose.putExtra("date_return", "101090101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.shenyang:
                intent_choose.putExtra("date_return", "101070101");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.chongqing:
                intent_choose.putExtra("date_return", "101040100");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            case R.id.tianjin:
                intent_choose.putExtra("date_return", "101030100");
                Log.d(TAG, "onItemClick: 他开始传入要打开的值了");
                setResult(2, intent_choose);
                finish();
                break;
            default:
                break;
        }
    }
}