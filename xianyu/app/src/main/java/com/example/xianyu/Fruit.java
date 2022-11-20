package com.example.xianyu;

public class Fruit {

    private int fruit_image;

    private  String fruit_name;

    private String fruit_money_number;

    private int seller_image;

    private String seller_name;

    public Fruit(int fruit_image, String fruit_name, String fruit_money_number, int seller_image, String seller_name) {
        this.fruit_image = fruit_image;
        this.fruit_name = fruit_name;
        this.fruit_money_number = fruit_money_number;
        this.seller_image = seller_image;
        this.seller_name = seller_name;
    }

    public int getFruit_image() {
        return fruit_image;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public String getFruit_money_number() {
        return fruit_money_number;
    }

    public int getSeller_image() {
        return seller_image;
    }

    public String getSeller_name() {
        return seller_name;
    }
}
