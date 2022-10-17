package com.example.myweather.get_Background_image;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Background {

    public static String json_data = null;

    public static void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1").build();
                    Response response = client.newCall(request).execute();
                    json_data = response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String parseJSONWithJSONObject(String jsonData){
        Gson gson = new Gson();
        geturl geturl = gson.fromJson(jsonData, com.example.myweather.get_Background_image.geturl.class);
        String url_image = "http://cn.bing.com" + geturl.url1_list.get(0).url2;
        return url_image;
    }

}
