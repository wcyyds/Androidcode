package com.example.myweather.net;

import com.google.gson.annotations.SerializedName;

public class today {

    @SerializedName("temperature")
    public String wendu;

    @SerializedName("phrase")
    public String tianqi;

    @SerializedName("air")
    public String kongqi;

    @SerializedName("air_level")
    public String kongqizhiliang;

    @SerializedName("feelsLike")
    public String tiganwendu;

    @SerializedName("humidity")
    public String shidu;

    @SerializedName("altimeter")
    public String qiya;

    @SerializedName("windSpeed")
    public String fengsu;

    @SerializedName("windDirCompass")
    public String fengxiangbiao;

}
