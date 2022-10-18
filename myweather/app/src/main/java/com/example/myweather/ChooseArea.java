package com.example.myweather;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myweather.lei.choose;
import com.example.myweather.lei.chooseAdapter;
import com.example.myweather.net.Weather;
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

public class ChooseArea extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    private Button back_main;

    private Button search_city;

    //private List<choose> chooseList = new ArrayList<>();

    URL url = null;

    Weather weather = null;

    private List<Choose_city_list> choose_city_list = new ArrayList<>();
    private List<String> choose_city_list1 = new ArrayList<>();

    //private List<Weather> chooseList123 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);

        //初始化各种控件
        back_main = (Button) findViewById(R.id.back_main);
        search_city = (Button) findViewById(R.id.start_search);
        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_city_list.clear();
                choose_city_list1.clear();
                Intent intent = new Intent(ChooseArea.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        search_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ChooseArea.this, com.example.myweather.search_city.class);
                choose_city_list.clear();
                choose_city_list1.clear();
                startActivityForResult(intent2,2);
            }
        });
//        for (int i = 0; i < cityid.size(); i++) {
//            Weather weather1 = requestdayweather(cityid.get(i));
//            huancun.add(weather1);
//            Log.d(TAG, "onCreate: " + huancun.get(i).request_city);
//        }

        //把cityid的东西全部弄到一个chooseList123的链表里面,存的是各个城市的weather类
//        Log.d(TAG, "beforefor: " + cityid.size());
//        for(int i = 0; i < cityid.size(); i++){
//            requestweather(cityid.get(i));
//            Log.d(TAG, "onCreate: *****************" + cityid);
//        }

//        initchoose();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.choose_city);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        chooseAdapter adapter = new chooseAdapter(chooseList);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                Log.d(TAG, "888: 进入到了swich语句当中");
                if(resultCode == 2){
                    Log.d(TAG, "8888: 进入到if判断中去了");
                    String choose_city = data.getStringExtra("date_return");

                    //
                    Log.d(TAG, "8888: " + choose_city);
                    choose_city_list.clear();
                    choose_city_list1.clear();
                    Intent intent = new Intent(ChooseArea.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        choose_city_list.clear();
        choose_city_list1.clear();
        Intent intent = new Intent(ChooseArea.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = new MyDatabaseHelper(this, " BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询book表中的所有数据
        Cursor cursor = db.query("Book", null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String SQL_cityname = cursor.getString(cursor.getColumnIndex("city"));
                @SuppressLint("Range") String SQL_cityId = cursor.getString(cursor.getColumnIndex("cityid"));
                Log.d(TAG, "onCreate: city  " + SQL_cityname);
                Choose_city_list shujuku = new Choose_city_list(SQL_cityId,SQL_cityname);
                choose_city_list.add(shujuku);
                choose_city_list1.add(shujuku.getCity_NAME());
            } while (cursor.moveToNext());
        }
        //删除数据库中的所有内容

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChooseArea.this, android.R.layout.simple_list_item_1,choose_city_list1);
        ListView listView_choose = (ListView) findViewById(R.id.list_view_choose);
        listView_choose.setAdapter(adapter);

        listView_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                choose_city_list.clear();
                choose_city_list1.clear();
                Intent intent = new Intent(ChooseArea.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listView_choose.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseArea.this);
                builder.setTitle("提示！");
                builder.setMessage("确定删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        Choose_city_list city_delete_cahngan = choose_city_list.get(position);
                        choose_city_list.remove(position);
                        choose_city_list1.remove(position);
                        db.delete("Book", "city = ?", new String[]{city_delete_cahngan.getCity_NAME()});
                        db.delete("Book", "cityid = ?", new String[]{city_delete_cahngan.getCity_ID()});
                        adapter.notifyDataSetChanged();
                        listView_choose.setSelection(0);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                // 返回true避免与点击事件冲突
                return true;
            }
        });

    }




/**


    private void initchoose() {
//        for(Weather weather : chooseList123){
//            choose choose123 = new choose(weather.request_city, weather.today.kongqizhiliang,
//                    weather.today.tiganwendu,weather.today.tiganwendu,
//                    weather.today.wendu,R.drawable.wu);
//            Log.d( "initchoose: " , weather.request_city);
//            chooseList.add(choose123);
//        }

        for (int i = 0; i < 3; i++) {
            choose choose1 = new choose("鄠邑区", "严重污染", "15℃", "26℃", "10℃", R.drawable.wu);
            chooseList.add(choose1);
            choose choose2 = new choose("长安区", "严重污染严重污染", "15℃", "26℃", "10℃", R.drawable.qing);
            chooseList.add(choose2);
            choose choose3 = new choose("蓝天县", "优", "15℃", "26℃", "10℃", R.drawable.lei);
            chooseList.add(choose3);
            choose choose4 = new choose("阎良区", "良好", "15℃", "26℃", "10℃", R.drawable.yin);
            chooseList.add(choose4);
        }
    }

    public void json(String json){
        Gson gson = new Gson();
        weather = gson.fromJson(json, Weather.class);
        Log.d(TAG, "run: ----------------------------------");
        Log.d(TAG, "choosearea: " + weather.request_city);
        Log.d(TAG, "run: ----------------------------------");
        chooseList123.add(weather);
        Log.d(TAG, "run:987654321 " + chooseList123.get(0).request_city);
    }

    public void requestweather(String address) {
        Log.d(TAG, "requestdayweather: 进入到了requestdayweather");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("ChooseArea", "run: 开始跑到run里面了");
                HttpURLConnection connection = null;
                Log.d("ChooseArea", "run: 开始跑到run里面了2222");
                BufferedReader reader = null;
                Log.d("ChooseArea", "run: 开始跑到run里面了33333");
                try {
                    Log.d("ChooseArea", "run: 开始跑到run里面了8888888");
                    if (address.equals("")) {
                        Log.d("ChooseArea", "run: 开始跑到run里面了if");
                        url = new URL("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ");
                    } else {
                        Log.d("ChooseArea", "run: 开始跑到run里面了else");
                        url = new URL("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ&cityid=" + address);
                    }
                    Log.d("ChooseArea", "run: 开始跑到run里面了1111");
                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("ChooseArea", "run: 开始跑到run里面了77777");
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Log.d("ChooseArea", "run: 开始跑到run里面了99999");
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    Log.d("ChooseArea", "run: 开始读取了");
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d("ChooseArea", "run: " + response.toString());
                    Log.d("ChooseArea", "run: 跑完了");
                    json(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

 */
}