package com.example.livedatalearncode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<String> mLiveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLiveData = new MutableLiveData<>();
        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("Main", "onChanged  " + s);
            }
        });
        Log.d("Main", "onCreate");
        mLiveData.setValue("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main", "onStart");
        mLiveData.setValue("onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main", "onResume");
        mLiveData.setValue("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main", "onPause");
        mLiveData.setValue("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main", "onStop");
        mLiveData.setValue("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main", "onDestroy");
        mLiveData.setValue("onDestroy");
    }



}