package com.example.customviewlearntest02java;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengService extends Service {
    //我们只需要在服务端创建一个Service来处理客户端的连接请求,同时创建一个Handler并
    //通过它来创建一个Messager对象,然后在Service的onBind中返回这个Messager对象底层的Binder即可

    private static  final String TAG = "MessengService";


    private static class MessengerHandler extends Handler{
        //MessengerHandler是用来处理客户端发送的消息的,并且从消息中去除客户端发来的文本信息
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Log.i(TAG, "receive msg from Client" +
                            msg.getData().getString("msg"));
                    //收到消息,会立刻回复一条消息给客户端
                    Messenger client = msg.replyTo;
                    Message relpyMessage = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯,你的消息我已经收到,稍后我回复给你");
                    relpyMessage.setData(bundle);
                    try {
                        client.send(relpyMessage);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    //messengerHandler是一个Messenger对象,
    // 它和MessengerHandler相关连,并在onBind方法中按返回它的Binder对象

    //可以看出来,Message的作用是将客户端中的消息传递给MessengerHandler进行处理的
    private final Messenger messengerHandler  =
            new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messengerHandler.getBinder();
    }
}
