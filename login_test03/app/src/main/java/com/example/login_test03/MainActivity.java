package com.example.login_test03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "测试";

    user user1 = new user();
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
        Log.d(TAG, "login: 开始登录");
        OkHttpClient client1 = new OkHttpClient();
        RequestBody requestBody1 = new FormBody.Builder()
                .add("phone", user1.getUesername())
                .add("password", user1.getPassword())
                .add("code", user1.getVerification())
                .build();
        Request request1 = new Request.Builder()
                .url("https://43.138.88.123/user/login")
                .post(requestBody1)
                .build();
        Response response1 = client1.newCall(request1).execute();
        Toast.makeText(MainActivity.this, response1.toString(), Toast.LENGTH_SHORT).show();
    }

    private void login1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://43.138.88.123/user/login");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("phone=" + user1.getUesername() +
                            "&password=" + user1.getPassword() +
                            "&code" + user1.getVerification());
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获取的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } catch (IOException e) {
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

    private void sendRequest1() {
        Log.d(TAG, "sendRequest1: 现在就进入到了验证码里面了，但是还没有进入到新的线程里面");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 现在进入到了新的线程里面了");
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://101.42.38.110/api/v1/user/code");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    Log.d(TAG, "run: 现在是发送了post请求");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    Log.d(TAG, "run: 现在是");
                    out.writeBytes("phone=" + user1.getUesername() +
                            "&password=" + "");
                    Log.d(TAG, "run: 现在是把那个推送出去了");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获取的输入流进行读取
                    Log.d(TAG, "run: 现在正在对获取的输入流进行读取");
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                } catch (IOException e) {
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

    private void sendRequest() throws IOException, JSONException {
        Log.d(TAG, "sendRequest: 开始进入到新的线程中去");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "sendRequest: 开始请求验证码");
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("email", username.getText().toString())
                            .build();
                    Log.d(TAG, "run: 这是输入的邮箱" + user1.getVerification());
                    Log.d(TAG, "run: 这是直接从输入框获取的邮箱" + username.getText().toString());

                    Request request = new Request.Builder()
                            .url("http://101.42.38.110/api/v1/user/code")
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    user1.setVerification(response.body().string());
                    Log.d(TAG, "run: 直接展示了请求到的代码" + response.body().string());
                    showResponse(user1.getVerification());
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getverification:
                Log.d(TAG, "onClick: 进入到了获取验证码里面");
                user1.setUesername(username.getText().toString());
                Log.d(TAG, "onClick: 这是在最开始获取的邮箱" + user1.getUesername());
                user1.setPassword(password.getText().toString());
                Log.d(TAG, "onClick: 现在准备进入到请求验证码里面");
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