package com.example.kotlinxiechenglearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kotlinx.coroutines.GlobalScope;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    int  it = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this,"这是什么",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}