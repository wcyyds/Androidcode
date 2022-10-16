package com.example.myweather.lei;

//这个是选择城市的recycleview
public class choose {

    private String city;

    private String air;

    private String item;//实时温度

    private String item1;//最高温度

    private String item2;//最低温度

    private int wea_img; //天气对应图标,只有9种类型.xue、lei、shachen、wu、bingbao、yun、yu、yin、qing

    public choose(String city, String air, String item1, String item2, String item, int wea_img) {
        this.city = city;
        this.air = air;
        this.item = item;
        this.item1 = item1;
        this.item2 = item2;
        this.wea_img = wea_img;
    }

    public String getCity() {
        return city;
    }

    public String getAir() {
        return air;
    }

    public String getItem() {
        return item;
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public int getWea_img() {
        return wea_img;
    }
}
