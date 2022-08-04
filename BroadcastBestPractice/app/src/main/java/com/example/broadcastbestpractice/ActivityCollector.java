package com.example.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activates = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activates.add(activity);
    }

    public static void removeActivity(Activity activity){
        activates.remove(activity);
    }

    public static  void finishAll(){
        for(Activity activity : activates){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activates.clear();
    }
}
