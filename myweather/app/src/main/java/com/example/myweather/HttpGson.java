package com.example.myweather;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.myweather.net.Weather;
import com.example.myweather.net.todayhours;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGson {

    URL url;

    //根据请求的网络IP,接口自动返回当前IP的今天的天气情况
    public void sendRequest(String address){
        Log.d(TAG, "requestdayweather: 进入到了requestdayweather");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 开始跑到run里面了");
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    if(address.equals("")){
                        url = new URL("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ");
                    }else{
                        url = new URL("https://v0.yiketianqi.com/api/worldchina?appid=38383686&appsecret=KWo8NTsJ&cityid=" + address);
                    }
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


                    //showdayweather(weather);
//                    Log.d(TAG, "run111: " + weather.today.wendu);
//                    Log.d(TAG, "run222: " + weather.today.tianqi);
//                    Log.d(TAG, "run333: " + weather.today.kongqi);
//                    Log.d(TAG, "run444: " + weather.today.kongqizhiliang);
//                    Log.d(TAG, "run555: " + weather.today.tiganwendu);
//                    Log.d(TAG, "run666: " + weather.today.shidu);
//                    Log.d(TAG, "run777: " + weather.today.qiya);
//                    Log.d(TAG, "run888: " + weather.today.fengsu);
//                    Log.d(TAG, "run999: " + weather.today.fengxiangbiao);
//                    for(com.example.myweather.net.dayweather dayweather : weather.dayslist){
//                        Log.d(TAG, "run1: " + dayweather.daysriqi);
//                        Log.d(TAG, "run2: " + dayweather.richu);
//                        Log.d(TAG, "run3: " + dayweather.riluo);
//                        Log.d(TAG, "run4: " + dayweather.daysday.daystianqi);
//                        Log.d(TAG, "run5: " + dayweather.daysday.dayswendu);
//                        Log.d(TAG, "run6: " + dayweather.daysday.daysgaishu);
//                        Log.d(TAG, "run7: " + dayweather.daysday.daysgailv);
//                    }
//                    for (com.example.myweather.net.todayhours todayhours : weather.todayhourslist){
//                        Log.d(TAG, "abc:1 " + todayhours.hourtianqi);
//                        Log.d(TAG, "abc:2 " + todayhours.hourtianqiimg);
//                        Log.d(TAG, "abc:3 " + todayhours.hourtime);
//                        Log.d(TAG, "abc:4 " + todayhours.hourair);
//                        Log.d(TAG, "abc:5 " + todayhours.hourwendu);
//                    }
                    //inittimehours(weather);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Weather JSONwithGSON(String jsondata){
        Weather weather;
        Gson gson = new Gson();
        weather = gson.fromJson(jsondata, Weather.class);
        Log.d(TAG, "run: ----------------------------------");
        Log.d(TAG, "choosearea: " + weather.request_city);
        Log.d(TAG, "run: ----------------------------------");
        return weather;
    }

}
