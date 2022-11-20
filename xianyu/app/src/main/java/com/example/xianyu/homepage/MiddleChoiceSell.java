package com.example.xianyu.homepage;

public class MiddleChoiceSell {

    private String big_message;

    private String small_message;

    private int imageId;

    public MiddleChoiceSell(String big_message, String small_message, int imageId) {
        this.big_message = big_message;
        this.small_message = small_message;
        this.imageId = imageId;
    }

    public String getBig_message() {
        return big_message;
    }

    public String getSmall_message() {
        return small_message;
    }

    public int getImageId() {
        return imageId;
    }
}
