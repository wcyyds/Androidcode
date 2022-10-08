package com.example.weather;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBar {

    private Activity activity;

    public StatusBar(Activity activity){
        this.activity = activity;
    }

    //将状态栏设置为传入的colo
    public void setStatusBarColor(int color){
        View view = activity.getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
    }


    //隐藏状态栏
    public void hideStatusBar(){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
    }


    //设置状态栏的字体
    public void setTextColor(boolean isDarkBackground){
        View decor = activity.getWindow().getDecorView();
        if(isDarkBackground){
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else{
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
