package com.example.busquery;

import android.app.DownloadManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;


public class Okhttp {

//    private void sendRequestWithOkHttp(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("这里是网址").build();
//                    Response response;
//                    response = client.newCall(request).execute();
//                    String responsedata = response.body().string();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private  void  parseJSONWithGSON(String jsonData){
//        Gson gson = new Gson();
//        city city = gson.fromJson(jsonData,new TypeToken<List<city>>(){}.getType());
//        List<returl_list> returl_lists = city.getReturl_lists();
//        for(returl_list re : returl_lists){
//            Log.d("xuanze", "parseJSONWithGSON: " + re.getCity());
//            Log.d("xuanze", "parseJSONWithGSON: " + re.getCityid());
//        }
//    }


}
