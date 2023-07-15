package com.example.databindinglearntest04java;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ObSwordsman extends BaseObservable {
    private String name;

    private String level;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   @Bindable
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ObSwordsman(String name, String level) {
        this.name = name;
        this.level = level;
    }
}
