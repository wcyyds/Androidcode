package com.example.myweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
//没有使用了
public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

//    //将返回的json数据解析成为weather实体类
//    public static Weather handleWeatherResponse(String response){
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(response);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JSONArray jsonArray = jsonObject.getJSONArray()
//    }
}
