package com.example.login_test03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "测试";

    User user = User.getInstance();

    TextView textView;
    Button getverification;
    Button login;
    EditText verification;
    EditText password;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: 初始化");
        init();
    }

    private void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        verification = (EditText) findViewById(R.id.verification);
        login = (Button) findViewById(R.id.login);
        getverification = (Button) findViewById(R.id.getverification);
        login.setOnClickListener(this);
        getverification.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textview);
    }

    private void login() throws IOException {
        Log.d(TAG, "login: 开始进入到新的线程中去");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject jsonObject = new JSONObject();

                    Log.d(TAG, "login: 开始登录");

                    jsonObject.put("email",user.getUesername());
                    jsonObject.put("code",verification.getText().toString());
                    jsonObject.put("password",password.getText().toString());

                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));

                    Request request = new Request.Builder()
                            .url("http://101.42.38.110/api/v1/user/register")
                            .post(requestBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onFailure: 失败的原因" + e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d(TAG, "onResponse: 这里是成功接收的" + response.body().string());
                            user.setUesername(username.getText().toString());
                            user.setPassword(password.getText().toString());
                            user.setVerification(password.getText().toString());
                            Log.d(TAG, "onResponse: 成功接收，并记录成功");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequest() throws IOException, JSONException {
        Log.d(TAG, "sendRequest: 开始进入到新的线程中去");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject jsonObject = new JSONObject();

                    Log.d(TAG, "sendRequest: 开始请求验证码");

                    jsonObject.put("email",username.getText().toString());


                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));

                    Request request = new Request.Builder()
                            .url("http://101.42.38.110:9999/api/v1/user/code")
                            .post(requestBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onFailure: 失败的原因" + e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d(TAG, "onResponse: 这里是成功接收的" + response.body().string());
                            user.setUesername(username.getText().toString());
                            Log.d(TAG, "onResponse: 成功接收，并记录成功");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(String response) {
        Log.d(TAG, "showResponse: 已经请求到了验证码，开始展示到桌面上面");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }

    private void parseJSONWithGSON(String response){
        Gson gson = new Gson();
        Information_back person = new Information_back();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            person = gson.fromJson(response, Information_back.class);
            Log.d(TAG, "parseJSONWithGSON: 接收的数据" + person.getData() + person.getCode()
            + person.getMsg() + person.getErr());
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getverification:
                try {
                    sendRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.login:
                Log.d(TAG, "onClick: 进入到了登录里面");
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}