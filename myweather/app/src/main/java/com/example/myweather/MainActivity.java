package com.example.myweather;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;

    private ScrollView weatherlayout;

    //这四个是从day中获得到的天气
    private TextView title_city;

    private TextView wendu;

    private TextView tianqi;

    private TextView sheshidu;

    private TextView airzhiliang;

    private String GPScity = null;

    private LinearLayout forecastLayout;

    private List<timehour> timehourList = new ArrayList<>();
    public timehour timehour123;

    private ImageView bingPicImg;

    //detail
    private TextView rise_time;

    private TextView set_time;

    private TextView wind_speed;

    private TextView wind_direction;

    private  TextView air_humidity;

    private TextView feels_like;

    private TextView air_altimeter;

    private  TextView SHIDU;

    private TextView TIGAN;

    private TextView QIYA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //表示同意百度的隐私接口可以使用百度的定位
        LocationClient.setAgreePrivacy(true);
        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLocationClient.registerLocationListener(new MyLocationListener());

        setContentView(R.layout.activity_main);

        //集体申请gps的定位,给了文件和定位的权限,注释了联系人的权限,联系人哪有点问题.
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            Log.d("123456", "123444444444");
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
//        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!=
//                PackageManager.PERMISSION_GRANTED){
//            Log.d("123456", "1234444444445555555");
//            permissionList.add(Manifest.permission.READ_PHONE_STATE);
//        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            Log.d("123456", "123666666666");
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            Log.d("123456", "开始给权限了");
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            //开始给权限了
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }else {
            Log.d("123456", "开始调用定位了" );
            requestLocation();
        }

        //初始化各种控件
       bingPicImg = (ImageView) findViewById(R.id.binf_pic_img);
        Log.d(TAG, "onCreate: 开始进入更新图片");
        loadBingPic();
        //more按钮,用来跑到另外一个活动
        Button more = (Button) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseArea.class);
                startActivity(intent);
            }
        });

        Log.d(TAG, "onCreate: 有没有请求网络IP这一步");
        requestdayweather();

        weatherlayout = (ScrollView) findViewById(R.id.weather_layout);
        title_city = (TextView) findViewById(R.id.title_city);
        wendu = (TextView) findViewById(R.id.wendu);
        tianqi = (TextView) findViewById(R.id.tianqi);
        sheshidu = (TextView) findViewById(R.id.sheshidu);
        airzhiliang = (TextView) findViewById(R.id.airzhiliang);

        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        hourAdapter hourAdapter = new hourAdapter(timehourList);
        recyclerView.setAdapter(hourAdapter);

        //detail
        rise_time = (TextView) findViewById(R.id.sunrise_time) ;

        set_time = (TextView) findViewById(R.id.sunset_time) ;

        wind_speed = (TextView) findViewById(R.id.WindSpeed) ;

        air_humidity = (TextView) findViewById(R.id.Humidity) ;

        feels_like = (TextView) findViewById(R.id.FeelsLike) ;

        air_altimeter = (TextView) findViewById(R.id.Altimeter) ;

        wind_direction = (TextView) findViewById(R.id.WindDirCompass) ;

        SHIDU = (TextView) findViewById(R.id.shidu);

        TIGAN = (TextView) findViewById(R.id.tigan);

        QIYA = (TextView) findViewById(R.id.qiya);

    }

    private void inittimehours(Weather weather){
        for (todayhours todayhours : weather.todayhourslist){
            timehour timehour123 = new timehour(todayhours.hourtime,todayhours.hourwendu,
                    R.drawable.wu,todayhours.hourair);
            timehourList.add(timehour123);
        }
//        for(int i = 0; i < 3; i++){
//            timehour one = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(one);
//            timehour two = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(two);
//            timehour three = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(three);
//            timehour four = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(four);
//            timehour five = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(five);
//            timehour six = new timehour("13:00", 15, R.drawable.wu,5);
//            timehourList.add(six);
//        }
    }



    private void loadBingPic() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    Log.d(TAG, "run:开始对获取的输入流进行读取");
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    parseJSONWithJSONObject(response.toString());
                    //Ui线程
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                Glide.with(MainActivity.this).load(response).into(bingPicImg);
                //  text.setText(response);
                Log.i("123",response);
                Log.d("213", "run: 跑到这里了" + response);
            }
        });
    }
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            Log.d(TAG, "parseJSONWithJSONObject: 开始对网址信息进行选取和拼接");
            JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("images");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = jsonObject.getString("url");

                Log.d("MainActivity", "url is " + url);
                String url1="http://cn.bing.com"+url;
                showResponse(url1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 根据请求的网络IP,接口自动返回当前IP的今天的天气情况
     */
    public void requestdayweather(){
        Log.d(TAG, "requestdayweather: 进入到了requestdayweather");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 开始跑到run里面了");
                //这个是OKhttp但是返回不到
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ")
//                        .build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    Log.d(TAG, "run: 开始请求网络了");
//                    String responsData = response.body().toString();
//                    Log.d(TAG, "run: " + responsData);
//                    Log.d(TAG, "run: 网络请求完了");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                //使用的是httpURL
                //这个使用的是URLhttp,不知道为什么OKhttp用不了
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ");
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
                    Weather weather= gson.fromJson(response.toString() , Weather.class);
                    showdayweather(weather);
                    Log.d(TAG, "run111: " + weather.today.wendu);
                    Log.d(TAG, "run222: " + weather.today.tianqi);
                    Log.d(TAG, "run333: " + weather.today.kongqi);
                    Log.d(TAG, "run444: " + weather.today.kongqizhiliang);
                    Log.d(TAG, "run555: " + weather.today.tiganwendu);
                    Log.d(TAG, "run666: " + weather.today.shidu);
                    Log.d(TAG, "run777: " + weather.today.qiya);
                    Log.d(TAG, "run888: " + weather.today.fengsu);
                    Log.d(TAG, "run999: " + weather.today.fengxiangbiao);
                    for(dayweather dayweather : weather.dayslist){
                        Log.d(TAG, "run1: " + dayweather.daysriqi);
                        Log.d(TAG, "run2: " + dayweather.richu);
                        Log.d(TAG, "run3: " + dayweather.riluo);
                        Log.d(TAG, "run4: " + dayweather.daysday.daystianqi);
                        Log.d(TAG, "run5: " + dayweather.daysday.dayswendu);
                        Log.d(TAG, "run6: " + dayweather.daysday.daysgaishu);
                        Log.d(TAG, "run7: " + dayweather.daysday.daysgailv);
                    }
                    for (todayhours todayhours : weather.todayhourslist){
                        Log.d(TAG, "abc:1 " + todayhours.hourtianqi);
                        Log.d(TAG, "abc:2 " + todayhours.hourtianqiimg);
                        Log.d(TAG, "abc:3 " + todayhours.hourtime);
                        Log.d(TAG, "abc:4 " + todayhours.hourair);
                        Log.d(TAG, "abc:5 " + todayhours.hourwendu);
                    }
                    inittimehours(weather);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                List<day> dayList = gson.fromJson(, new TypeToken<List<day>>(){}.getType());
//                Log.d(TAG, "run: 网络请求已经放到了链表中");
//                for(day day1 : dayList){
//                    Log.d(TAG, "run: " + day1.getTemperatere());
//                    Log.d(TAG, "run: " + day1.getPhrase());
//                    Log.d(TAG, "run: " + day1.getAir());
//                    Log.d(TAG, "run: " + day1.getAir_level());
//                }
            }
        }).start();

    }

    public void showdayweather(Weather weather) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String Temperatere = weather.today.wendu;
                String phrase = weather.today.tianqi;
                String air = weather.today.kongqi;
                String air_level = weather.today.kongqizhiliang;
                wendu.setText(Temperatere);
                tianqi.setText(phrase);
                sheshidu.setText("℃");
                airzhiliang.setText("空气 " + air_level +" " + air);
                title_city.setText(GPScity);

                int i = 0;
                forecastLayout.removeAllViews();
                for(dayweather day123 : weather.dayslist){
                    if(i < 3) {
                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.forecast_item, forecastLayout, false);
                        TextView datatext = (TextView) view.findViewById(R.id.date_text);
                        TextView weathertext = (TextView) view.findViewById(R.id.weather_text);
                        TextView temtext = (TextView) view.findViewById(R.id.tem_text);
                        weathertext.setText(day123.daysday.daystianqi);
                        temtext.setText(day123.daysday.dayswendu + "℃");
                        datatext.setText(day123.daysriqi);
                        forecastLayout.addView(view);
                        i++;
                    }else{
                        break;
                    }
                }

                rise_time.setText("日出" + weather.dayslist.get(0).richu);
                set_time.setText("日落" + weather.dayslist.get(0).riluo);

                wind_speed.setText(weather.today.fengsu + "级");
                air_humidity.setText(weather.today.shidu + "%");
                feels_like.setText(weather.today.tiganwendu + "°");
                air_altimeter.setText(weather.today.qiya + "hpa");
                wind_direction.setText(weather.today.fengxiangbiao);
                SHIDU.setText("湿度");
                TIGAN.setText("体感");
                QIYA.setText("气压");


            }
        });
    }




    //开始定位了,.start
    private void requestLocation(){
        initLocation();
        Log.d("123456", "开始定位了");
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }


    //申请权限,没有权限的不给你过,直接关闭应用
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    /**
     *获取gps的信息给显示本地抬头的城市
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
                    currentPosition.append("经度："). append(bdLocation.getLongitude()).append("\n");
                    currentPosition.append("国家：").append(bdLocation.getCountryCode()).append("\n");
                    currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("市:").append(bdLocation.getCity()).append("\n");
                    currentPosition.append("区:").append(bdLocation.getDistrict()).append("\n");
                    currentPosition.append("乡镇:").append(bdLocation.getTown()).append("\n");
                    currentPosition.append("街道:").append(bdLocation.getStreet()).append("\n");
                    currentPosition.append("代码adCode:").append(bdLocation.getAdCode()).append("\n");
                    currentPosition.append("位置描述信息:").append(bdLocation.getLocationDescribe()).append("\n");
                    currentPosition.append("位置描述id:").append(bdLocation.getLocationID()).append("\n");
                    currentPosition.append("所有省份:").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("定位方式: ");
            Log.d("MyLocationListener", "获得的gps信息: " + currentPosition);
            GPScity = bdLocation.getCity() + bdLocation.getDistrict();
        }
        public void onConnectHotSpotMessage(String s, int t){

        }
    }


}