package com.example.myweather;

public class Choose_city_list {
    private String city_ID;
    private String city_NAME;

    public String getCity_ID() {
        return city_ID;
    }

    public void setCity_ID(String city_ID) {
        this.city_ID = city_ID;
    }

    public String getCity_NAME() {
        return city_NAME;
    }

    public void setCity_NAME(String city_NAME) {
        this.city_NAME = city_NAME;
    }

    public Choose_city_list(String city_ID, String city_NAME) {
        this.city_ID = city_ID;
        this.city_NAME = city_NAME;
    }
}
