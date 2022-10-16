package com.example.myweather.lei;

//这是主界面的hour的recycleview
public class timehour {

    private String hour;

    private  int du;

    private String weather;

    private int air;

    public timehour(String hour, int du, String weather, int air) {
        this.hour = hour;
        this.du = du;
        this.weather = weather;
        this.air = air;
    }

    public String getHour() {
        return hour;
    }

    public int getDu() {
        return du;
    }

    public String getWeather() {
        return weather;
    }

    public int getAir() {
        return air;
    }
}
