package com.example.myweather;

import com.google.gson.annotations.SerializedName;

public class Daysnight {

    @SerializedName("phrase")
    public String daystianqi;

    @SerializedName("temperature")
    public String dayswendu;

    //天气情况概述
    @SerializedName("narrative")
    public String daysgaishu;

    //降雨概率
    @SerializedName("precipPct")
    public String daysgailv;

}
