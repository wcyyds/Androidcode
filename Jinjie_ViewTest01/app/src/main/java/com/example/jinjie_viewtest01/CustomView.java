package com.example.jinjie_viewtest01;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class CustomView extends androidx.appcompat.widget.AppCompatButton {

    Scroller mScroller;

    public static final String TAG = "CustomView";

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context) {
        super(context);
    }

    private int lastY;

    private int lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指触摸点的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN222");
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: ACTION_MOVE1111111");
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //调用layout方法来重新放置它的位置
                layout(getLeft()+offsetX,getTop()+offsetY,
                        getRight()+offsetX, getBottom()+offsetY);
                break;
            case  MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP333");
                //这里就可以证明这个layout方法是把bottom本身的属性一起变更的,不是只是动画变更了
                break;
        }
        return true;
    }
}
