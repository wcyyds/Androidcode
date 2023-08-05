package com.example.glidesourcecodelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.glidesourcecodelearn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);
        //imageView = findViewById(R.id.image_view);
    }

    public void loadImage(View view){
        RequestOptions options = new RequestOptions().
                placeholder(R.drawable.haizei);
        Log.d("loadImage", "loadImage: 111");
        String url = "https://pic1.zhimg.com/v2-da629d74bcc91d3aebbbe0b176d434e2_1440w.jpg?source=172ae18b";
        Glide.with(this).load(url)
                .apply(options).into(binding.imageView);
    }

}