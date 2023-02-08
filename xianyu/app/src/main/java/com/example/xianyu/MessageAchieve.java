package com.example.xianyu;

public class MessageAchieve {

    private int message_seller_image;

    private String message_seller_name;

    private String message_seller_message;

    private String message_time;

    private String message_seller_goods;

    public MessageAchieve(int message_seller_image, String message_seller_name,
                          String message_seller_message, String message_time, String message_seller_goods) {
        this.message_seller_image = message_seller_image;
        this.message_seller_name = message_seller_name;
        this.message_seller_message = message_seller_message;
        this.message_time = message_time;
        this.message_seller_goods = message_seller_goods;
    }

    public int getMessage_seller_image() {
        return message_seller_image;
    }

    public String getMessage_seller_name() {
        return message_seller_name;
    }

    public String getMessage_seller_message() {
        return message_seller_message;
    }

    public String getMessage_time() {
        return message_time;
    }

    public String getMessage_seller_goods() {
        return message_seller_goods;
    }
}
