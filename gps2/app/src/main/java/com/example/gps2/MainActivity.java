package com.example.gps2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationClient.setAgreePrivacy(true);
        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_main);
        positionText = (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            Log.d("123456", "123444444444");
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
//        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!=
//                PackageManager.PERMISSION_GRANTED){
//            Log.d("123456", "1234444444445555555");
//            permissionList.add(Manifest.permission.READ_PHONE_STATE);
//        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            Log.d("123456", "12344444444466666666");
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            Log.d("123456", "??????????????????");
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }else {
            Log.d("123456", "?????????????????????" );
            requestLocation();
        }
    }

    private void requestLocation(){
        initLocation();
        Log.d("123456", "???????????????");
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("?????????").append(bdLocation.getLatitude()).append("\n");
                    Log.d("123456", "run: " + bdLocation.getLatitude());
                    currentPosition.append("?????????"). append(bdLocation.getLongitude()).append("\n");
                    Log.d("654321", "run: " + bdLocation.getLatitude());
                    currentPosition.append("?????????").append(bdLocation.getCountryCode()).append("\n");
                    currentPosition.append("??????").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("???:").append(bdLocation.getCity()).append("\n");
                    currentPosition.append("???:").append(bdLocation.getDistrict()).append("\n");
                    currentPosition.append("??????:").append(bdLocation.getTown()).append("\n");
                    currentPosition.append("??????:").append(bdLocation.getStreet()).append("\n");
                    currentPosition.append("??????adCode:").append(bdLocation.getAdCode()).append("\n");
                    currentPosition.append("??????????????????:").append(bdLocation.getLocationDescribe()).append("\n");
                    currentPosition.append("????????????id:").append(bdLocation.getLocationID()).append("\n");
                    currentPosition.append("????????????:").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("????????????: ");
                    if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("??????");
                    }
                    positionText.setText(currentPosition);
                    Log.d("MyLocationListener", "?????????gps??????: " + currentPosition);
                }
            });
        }
        public void onConnectHotSpotMessage(String s, int t){

        }
    }
}

