package com.example.weather;

public class timehour {
    private String hour;

    private  String du;

    private int weather;

    private String air;

    public int getWeather() {
        return weather;
    }

    public timehour(String hour, String du, int weather, String air) {
        this.hour = hour;
        this.du = du;
        this.weather = weather;
        this.air = air;
    }

    public String getHour() {
        return hour;
    }

    public String getDu() {
        return du;
    }

    public String getAir() {
        return air;
    }
}
