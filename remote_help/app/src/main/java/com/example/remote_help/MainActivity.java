package com.example.remote_help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.remote_help.recycle.Family;
import com.example.remote_help.recycle.FamilyAdapter;
import com.example.remote_help.sharescreen.GetPhoneNumberActivity;
import com.example.remote_help.sqlite.Operate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private final String TAG = "MainActivity123456";

    private Operate operate = Operate.getInstance();

    //这是recycleview的链表,对recycleview操作在这上面进行
    List<Family> familyListlist = new ArrayList<>();

    private ImageButton add;

    private ImageButton delete;

    private ImageButton dadianhua;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: 这是测试活动的开关");

        //在这里初始化数据库
        operate.initSQL(this);

        //请求权限
        requestPermission();

        //打开获取来电服务
        incomingService(this);

        add = (ImageButton) findViewById(R.id.add);
        delete = (ImageButton) findViewById(R.id.delete);
        dadianhua = (ImageButton) findViewById(R.id.dadianhua);

        dadianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                Uri data = Uri.parse("tel:" + "18991377839");

                intent.setData(data);

                startActivity(intent);
            }
        });

        //添加联系人按钮
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //删除联系人按钮
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
    }

    //开启服务,不关闭活动就一直获取电话号码
    private void incomingService(MainActivity mainActivity){
        Intent startIncomingService = new Intent(this, IncomingCallService.class);

        startService(startIncomingService);
        Toast.makeText(this, "获取来电号码", Toast.LENGTH_SHORT).show();
    }

    //每次活动可见时,更新主页的recycleview的信息
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 这是测试活动的开关");
        Log.d("on", "onStart: 活动由不可见到可见的时候调用");
        initData();
        initRecycle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 这是测试活动的开关");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 这是测试活动的开关");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 这是测试活动的开关");
    }

    //在开始的活动关闭的时候,关闭获取来电的服务
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 这是测试活动的开关");
        //在销毁活动的时候,关闭获取电话的服务
        Intent stopIncomingService = new Intent(this, IncomingCallService.class);
        stopService(stopIncomingService);
        Toast.makeText(this, "不获取来电号码", Toast.LENGTH_SHORT).show();
    }

    //请求打电话,摄像头,录音,打电话权限
    private void requestPermission() {
        String[] permissionNeeded = {
                "android.permission.READ_CALL_LOG",
                "android.permission.READ_PHONE_STATE",
                "android.permission.CALL_PHONE",
                "android.permission.CAMERA",
                "android.permission.READ_PRIVILEGED_PHONE_STATE",
                "android.permission.READ_SMS",
                "android.permission.READ_PHONE_NUMBERS"};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_CALL_LOG") != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PHONE_STATE") != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CALL_PHONE") != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PRIVILEGED_PHONE_STATE") != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PHONE_NUMBERS") != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionNeeded, 101);
            }
        }
    }
    
    //这是启动一个碎片
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //初始化recycleview
    private void initRecycle(){
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FamilyAdapter adapter = new FamilyAdapter(familyListlist);
        adapter.setDeleteActivity(false,this);
        recyclerView.setAdapter(adapter);
    }

    //初始化recycleview的数据
    private void initData(){
        familyListlist.clear();
        familyListlist = operate.querySQL();
    }
}