package com.example.myweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("code")
    public String zhuangtai;

    @SerializedName("location")
    public List<search_city> search_cities;

}
