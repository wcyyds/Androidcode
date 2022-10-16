package com.example.myweather.net;

import com.google.gson.annotations.SerializedName;

public class dayweather {

    //日期
    @SerializedName("date")
    public String daysriqi;
    //日出
    @SerializedName("sunrise")
    public String richu;
    //日落
    @SerializedName("sunset")
    public String riluo;
    //白天
    @SerializedName("day")
    public Daysday daysday;
    //晚上
    @SerializedName("night")
    public Daysnight daysnight;
}
