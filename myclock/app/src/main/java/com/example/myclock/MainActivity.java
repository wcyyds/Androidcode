package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.webkit.WebView;

import com.alarmclockview.AlarmClockView;
import com.alarmclockview.TimeChangeListener;
import com.don.clockviewlibrary.ClockView;

import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ClockView clockView = (ClockView) findViewById(R.id.clockView);
        clockView.setOnCurrentTimeListener(new ClockView.OnCurrentTimeListener() {
            @Override
            public void currentTime(String time) {
                Log.i("MyLog", time);
            }
        });

        AlarmClockView mClock = findViewById(R.id.clock);
        mClock.start(new TimeChangeListener() {
            @Override
            public void onTimeChange(Calendar calendar) {
                calendar.get(Calendar.HOUR_OF_DAY);
            }
        });


    }
}