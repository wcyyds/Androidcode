package com.example.busquery2.OkHttp;

import android.util.Log;

import com.example.busquery2.GSON.CityListGson.City;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//公交路线
public class BusRoutes {

    private static final String TAG = "BUSROUTES";

    public void sendRequestWithOkHttp() {
        Log.d(TAG, "sendRequestWithOkHttp: 进入到方法sendRequestWithOkHttp");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: 进入");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.wxbus163.cn/z_busapi/BusApi.php?optype=city&uname=18991377839")
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onFailure: 请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d(TAG, "onResponse: " );
                            String responsedata = response.body().string();
                            parseJSONWithGSON(responsedata);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        //city = gson.fromJson(jsonData, City.class);
    }
}
