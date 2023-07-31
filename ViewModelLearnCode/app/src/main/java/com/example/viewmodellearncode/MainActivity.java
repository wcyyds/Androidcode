package com.example.viewmodellearncode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.viewmodellearncode.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.i("111", "onCreate: ");

        TextView tvUserName = findViewById(R.id.textView);
        ProgressBar pbLoading = findViewById(R.id.pb_loading);
        //获取ViewModel实例
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        UserViewModel userViewModel = viewModelProvider.get(UserViewModel.class);
        //观察 用户信息
        userViewModel.getUserLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // update ui.
                tvUserName.setText(s);
            }
        });

        userViewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                pbLoading.setVisibility(aBoolean? View.VISIBLE:View.GONE);
            }
        });
        //点击按钮获取用户信息
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.getUserInfo();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("111", "onStop: ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("111", "onDestroy: ");
    }
}
