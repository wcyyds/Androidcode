package com.example.myweather.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("city")
    public String request_city;

    @SerializedName("day")
    public today today;

    @SerializedName("hours")
    public List<todayhours> todayhourslist;

    @SerializedName("month")
    public List<dayweather> dayslist;

}
