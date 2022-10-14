package com.example.myweather;

import com.google.gson.annotations.SerializedName;

public class todayhours {

    @SerializedName("time")
    public String hourtime;

    @SerializedName("wea")
    public String hourtianqi;

    @SerializedName("wea_img")
    public String hourtianqiimg;

//这个是一个体感温度
    @SerializedName("temfeels")
    public int hourwendu;

    //空气质量
    @SerializedName("air")
    public int hourair;
}
