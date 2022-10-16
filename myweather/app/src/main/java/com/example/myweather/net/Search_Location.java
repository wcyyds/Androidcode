package com.example.myweather.net;

import com.google.gson.annotations.SerializedName;

public class Search_Location {

    @SerializedName("name")
    public String city_name;

    @SerializedName("id")
    public String city_id;

    @SerializedName("adm2")
    public String shi;

    @SerializedName("adm1")
    public String sheng;

    @SerializedName("country")
    public String guojia;

}
