package com.example.viewmodeltest;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel
{
    private static final String TAG = "TimerViewModel";
    private Timer timer;
    private int currentSecond;

    public TimerViewModel() {
    }

    public TimerViewModel(int currentSecond1) {
        currentSecond = currentSecond1;
    }

    /**
     * LiveData是抽象类,MutableLiveData是具体实现类
     */
    private MutableLiveData<String> content;

    public MutableLiveData<String> getContent(){
        if(content == null){
            content = new MutableLiveData<>();
        }
        return content;
    }

    /**
     * 开始计时
     * */
    public void startTiming()
    {
        if (timer == null)
        {
            timer = new Timer();
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    currentSecond++;
                    //在这里每一秒就postValue一次推给主界面进行UI的变化
                    content.postValue(String.valueOf(currentSecond));
                }
            };
            timer.schedule(timerTask, 2000, 1000);//延迟2秒执行
        }
    }

    /**
     * 由于屏幕旋转导致的Activity重建，该方法不会被调用
     *
     * 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源
     * */
    @Override
    protected void onCleared()
    {
        super.onCleared();
        timer.cancel();
    }
}
