package com.example.get_phone;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d(TAG, "handleMessage: + 这里是接收到了消息");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在这里进行一些耗时的操作

                Message message = Message.obtain();
                message.what = 1;
                mHandler.sendMessage(message);
                Log.d(TAG, "run: 这里是把消息发送出去了");
            }
        }).start();
    }
}