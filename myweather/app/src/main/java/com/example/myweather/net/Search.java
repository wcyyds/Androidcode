package com.example.myweather.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("code")
    public String zhuangtai;

    @SerializedName("location")
    public List<Search_Location> search_cities;

}
