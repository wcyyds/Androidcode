package com.example.view_pager2;

public class ViewPager {
    private String name;

    private int image;

    public ViewPager(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
