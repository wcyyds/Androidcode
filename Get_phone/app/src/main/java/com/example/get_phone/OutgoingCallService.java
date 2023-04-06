package com.example.get_phone;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCallService extends Service {
    /**
     * 去电 Action
     */
    private static final String OUTGOING_ACTION = "android.intent.action.NEW_OUTGOING_CALL";

    /**
     * 去电广播接收器
     */
    private MyPhoneStateReceiver myPhoneStateReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取去电号码
        getOutgoingCall();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 不获取去电号码
        getOutgoingCallCancel();
    }

    /**
     * 获取去电号码
     */
    private void getOutgoingCall() {
        IntentFilter intentFilter = new IntentFilter();
        // 监听去电广播
        intentFilter.addAction(OUTGOING_ACTION);
        myPhoneStateReceiver = new MyPhoneStateReceiver();
        // 动态注册去电广播接收器
        registerReceiver(myPhoneStateReceiver, intentFilter);
    }

    /**
     * 不获取去电号码
     */
    private void getOutgoingCallCancel() {
        // 取消注册去电广播接收器
        unregisterReceiver(myPhoneStateReceiver);
    }

    /**
     * 监听去电广播
     */
    class MyPhoneStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取去电号码
            String outgoingNumber = getResultData();
            Log.d("这是去电的电话号码", "onReceive: " + outgoingNumber);
            Toast.makeText(context, "去电号码是：" + outgoingNumber, Toast.LENGTH_LONG).show();
        }
        //https://blog.csdn.net/like_program/article/details/52662942?ops_request_misc=%257B%2
        // 522request%255Fid%2522%253A%2522168061389216800227449519%2522%252C%2522scm%2522%253
        // A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=168061389216800227449519&
        // biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm
        // _v1~rank_v31_ecpm-2-52662942-null-null.142^v81^insert_down1,201^v4^add_ask,239^v2^in
        // sert_chatgpt&utm_term=%E5%AE%89%E5%8D%93%E8%8E%B7%E5%8F%96%E6%9D%A5%E7%94%B5%E5%8E%B
        // B%E7%94%B5%E7%9A%84%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81&spm=1018.2226.3001.4187
    }
}