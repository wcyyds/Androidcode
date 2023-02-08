package com.example.moduletest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mine.BuildConfig;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ARouter后台有ILogger接口，定义了一些输出日志
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化ARouter
        ARouter.init(this);

    }
    private  boolean isDebug(){
        return BuildConfig.DEBUG;
    }
}
