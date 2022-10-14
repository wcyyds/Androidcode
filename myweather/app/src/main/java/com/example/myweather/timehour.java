package com.example.myweather;

public class timehour {
    private String hour;

    private  int du;

    private int weather;

    private int air;

    public int getWeather() {
        return weather;
    }

    public timehour(String hour, int du, int weather, int air) {
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

    public int getAir() {
        return air;
    }
}
