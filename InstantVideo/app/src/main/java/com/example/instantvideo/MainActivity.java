package com.example.instantvideo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.callback.IZegoDestroyCompletionCallback;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.constants.ZegoPlayerState;
import im.zego.zegoexpress.constants.ZegoPublishChannel;
import im.zego.zegoexpress.constants.ZegoPublisherState;
import im.zego.zegoexpress.constants.ZegoRoomStateChangedReason;
import im.zego.zegoexpress.constants.ZegoScenario;
import im.zego.zegoexpress.constants.ZegoStreamQualityLevel;
import im.zego.zegoexpress.constants.ZegoUpdateType;
import im.zego.zegoexpress.constants.ZegoVideoSourceType;
import im.zego.zegoexpress.entity.ZegoCanvas;
import im.zego.zegoexpress.entity.ZegoEngineProfile;
import im.zego.zegoexpress.entity.ZegoRoomConfig;
import im.zego.zegoexpress.entity.ZegoStream;
import im.zego.zegoexpress.entity.ZegoUser;
import im.zego.zegoexpress.entity.ZegoVideoConfig;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "啥";
    ZegoExpressEngine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tiaozhuan = (Button)findViewById(R.id.tiaozhuan);
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this,lianxi.class);
                startActivity(intent);
            }
        });


        // 在通话前需请求相应摄像头、录音权限
        requestPermission();

        // 开始通话按钮
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            // 点击开始通话
            @Override
            public void onClick(View view) {
                // 创建Express SDK 实例
                createEngine();
                // 监听常用事件
                setEventHandler();
                // 登录房间
                loginRoom();
                // 开始预览及推流
                startPublish();
            }
        });

        // 停止通话按钮
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            // 点击停止通话
            @Override
            public void onClick(View view) {
                engine.logoutRoom();
                ZegoExpressEngine.destroyEngine(new IZegoDestroyCompletionCallback() {
                    @Override
                    public void onDestroyCompletion() {
                        //销毁成功
                    }
                });
            }
        });
    }

    //请求权限
    private void requestPermission() {
//请求摄像头、录音权限
//        String[] permissionNeeded = {
//                "android.permission.CAMERA",
//                "android.permission.RECORD_AUDIO"};
//        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                Log.d("myself", "requestPermission: 看看有没有进入到这里面去");
//                requestPermissions(permissionNeeded, 101);
//            }
//        }

        //请求屏幕的权限
        String[] PERMISSIONS_STORAGE = {
                "android.permission.RECORD_AUDIO",
                "android.permission.FOREGROUND_SERVICE"};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.FOREGROUND_SERVICE") != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS_STORAGE, 101);
            }
        }
    }

    // 创建 ZegoExpress 实例，监听常用事件
    void createEngine() {
        // 创建引擎，通用场景接入，并注册 self 为 eventHandler 回调
        // 不需要注册回调的话，eventHandler 参数可以传 null，后续可调用 "setEventHandler:" 方法设置回调
        ZegoEngineProfile profile = new ZegoEngineProfile();
        profile.appID = 609408564L;  // 请通过官网注册获取，格式为：1234567890L
        profile.appSign = "59fb6b3d70836007ba898832b22da9917c76576b26436737a218dbd538eb33d7"; //请通过官网注册获取，格式为："0123456789012345678901234567890123456789012345678901234567890123"（共64个字符）
        profile.scenario = ZegoScenario.BROADCAST;  // 通用场景接入
        profile.application = getApplication();
        engine = ZegoExpressEngine.createEngine(profile, null);

        //enableHardwareEncoder是详情描述: 推流时是否采用硬件编码的开关，开启硬解编码后会使用 GPU 进行编码，降低 CPU 使用率。
        //调用时机: 在推流前设置才能生效，如果在推流后设置，停推后重新推流可以生效。
        //注意事项: 由于少部分机型设备硬编支持不是特别好，SDK 默认使用软件编码的方式。若开发者在某些机型测试时发现推大分辨率音视频流时设备发热严重，可考虑调用此函数开启硬编的方式。
        //打开硬件编码的开关
        engine.enableHardwareEncoder(true);
        //改变默认的摄像头推流源为屏幕共享源
        engine.setVideoSource(ZegoVideoSourceType.ZEGO_VIDEO_SOURCE_SCREEN_CAPTURE, ZegoPublishChannel.AUX);


    }

    public void setVideoConfig() {
        //视频配置信息
//        ZegoVideoConfig videoConfig = engine.getVideoConfig(ZegoPublishChannel.AUX);
//        engine.setVideoConfig(videoConfig, ZegoPublishChannel.AUX);
        //视频配置信息
        ZegoVideoConfig videoConfig = engine.getVideoConfig(ZegoPublishChannel.AUX);
//        videoConfig.encodeHeight = Integer.parseInt(encodeResolutionHeight.getText().toString());
//        videoConfig.encodeWidth = Integer.parseInt(encodeResolutionWidth.getText().toString());;
//        videoConfig.fps = Integer.parseInt(frameRateEdit.getText().toString());
//        videoConfig.bitrate = Integer.parseInt(bitrateEdit.getText().toString());
//        Log.d("检测", "setVideoConfig: " + encodeResolutionHeight.getText().toString());
//        Log.d("检测", "setVideoConfig: " + encodeResolutionWidth.getText().toString());
//        Log.d("检测", "setVideoConfig: " + frameRateEdit.getText().toString());
//        Log.d("检测", "setVideoConfig: " + bitrateEdit.getText().toString());
        engine.setVideoConfig(videoConfig, ZegoPublishChannel.AUX);
    }


//    bindView();
//    requestPermission();
//    setLogComponent();
//    getAppIDAndUserIDAndAppSign();
//    setDefaultValue();
//    initEngineAndUser();
//    prepareScreenCapture();
//    setPlayButtonEvent();
//    setEventHandler();
//    setStartScreenCaptureButtonEvent();
//    setApiCalledResult();

    //登录房间
    void loginRoom() {
        // ZegoUser 的构造方法 public ZegoUser(String userID) 会将 “userName” 设为与传的参数
        // “userID” 一样。“userID” 与 “userName” 不能为 “null” 否则会导致登录房间失败。
        ZegoUser user = new ZegoUser("user2");

        ZegoRoomConfig roomConfig = new ZegoRoomConfig();
        //如果您使用 appsign 的方式鉴权，token 参数不需填写；如果需要使用更加安全的 鉴权方式： token 鉴权，
        // 请参考[如何从 AppSign 鉴权升级为 Token 鉴权](https://doc-zh.zego.im/faq/token_upgrade?product=ExpressVideo&platform=all)
        //roomConfig.token = ;
        // 只有传入 “isUserStatusNotify” 参数取值为 “true” 的 ZegoRoomConfig，才能收到 onRoomUserUpdate 回调。
        roomConfig.isUserStatusNotify = true;

        // roomID 由您本地生成,需保证 “roomID” 全局唯一。不同用户要登录同一个房间才能进行通话
        String roomID = "room1";

        //配置视频信息
        setVideoConfig();
        //开始屏幕共享
        engine.startScreenCapture();

        // 登录房间
        engine.loginRoom(roomID, user, roomConfig, (int error, JSONObject extendedData)->{
            // 登录房间结果，如果仅关注登录结果，关注此回调即可
            if (error == 0) {
                // 登录成功
                Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            } else {
                // 登录失败，请参考 errorCode 说明 https://doc-zh.zego.im/article/4378
                Toast.makeText(this, "登录失败，请参考 errorCode 说明 https://doc-zh.zego.im/article/4378", Toast.LENGTH_LONG).show();
            }
        });
    }

    //预览并推流
    void startPublish() {
        // 设置本地预览视图并启动预览，视图模式采用 SDK 默认的模式，等比缩放填充整个 View
        ZegoCanvas previewCanvas = new ZegoCanvas(findViewById(R.id.preview));
        engine.startPreview(previewCanvas);

        // 开始推流
        // 用户调用 loginRoom 之后再调用此接口进行推流
        // 在同一个 AppID 下，开发者需要保证“streamID” 全局唯一，如果不同用户各推了一条 “streamID” 相同的流，后推流的用户会推流失败。
        Log.d(TAG, "startPublish: 开始推流");
        engine.startPublishingStream("stream2");
    }

    void setEventHandler() {
        engine.setEventHandler(new IZegoEventHandler() {

            @Override
            // 房间内其他用户推流/停止推流时，我们会在这里收到相应用户的音视频流增减的通知
            public void onRoomStreamUpdate(String roomID, ZegoUpdateType updateType, ArrayList<ZegoStream> streamList, JSONObject extendedData) {
                super.onRoomStreamUpdate(roomID, updateType, streamList, extendedData);
                //当 updateType 为 ZegoUpdateType.ADD 时，代表有音视频流新增，此时我们可以调用 startPlayingStream 接口拉取播放该音视频流
                if (updateType == ZegoUpdateType.ADD) {
                    // 开始拉流，设置远端拉流渲染视图，视图模式采用 SDK 默认的模式，等比缩放填充整个 View
                    ZegoStream stream = streamList.get(0);
                    String playStreamID = stream.streamID;
                    // 如下 remoteUserView 为 UI 界面上的 TextureView.这里为了使示例代码更加简洁，我们只拉取新增的音视频流列表中第的第一条流，在实际的业务中，建议开发者循环遍历 streamList ，拉取每一条音视频流
                    ZegoCanvas playCanvas = new ZegoCanvas(findViewById(R.id.remoteUserView));
                    engine.startPlayingStream(playStreamID, playCanvas);
                }
            }

            //同一房间内的其他用户进出房间时，您可通过此回调收到通知。回调中的参数 ZegoUpdateType 为 ZegoUpdateType.ADD 时，表示有用户进入了房间；ZegoUpdateType 为 ZegoUpdateType.DELETE 时，表示有用户退出了房间。
            // 只有在登录房间 loginRoom 时传的配置 ZegoRoomConfig 中的 isUserStatusNotify 参数为 true 时，用户才能收到房间内其他用户的回调。
            // 房间人数大于 500 人的情况下 onRoomUserUpdate 回调不保证有效。若业务场景存在房间人数大于 500 的情况，请联系 ZEGO 技术支持。
            @Override
            public void onRoomUserUpdate(String roomID, ZegoUpdateType updateType, ArrayList<ZegoUser> userList) {
                super.onRoomUserUpdate(roomID, updateType, userList);
                // 您可以在回调中根据用户的进出/退出情况，处理对应的业务逻辑
                if (updateType == ZegoUpdateType.ADD) {
                    for (ZegoUser user : userList) {
                        String text = user.userID + "进入了房间";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                    }
                } else if (updateType == ZegoUpdateType.DELETE) {
                    for (ZegoUser user : userList) {
                        String text = user.userID + "退出了房间";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                    }
                }
            }

            // 房间连接状态改变
            @Override
            public void onRoomStateChanged(String roomID, ZegoRoomStateChangedReason reason, int i, JSONObject jsonObject) {
                super.onRoomStateChanged(roomID, reason, i, jsonObject);
                if(reason == ZegoRoomStateChangedReason.LOGINING) {
                    // 正在登录房间。当调用 [loginRoom] 登录房间或 [switchRoom] 切换到目标房间时，进入该状态，表示正在请求连接服务器。通常通过该状态进行应用界面的展示。
                } else if(reason == ZegoRoomStateChangedReason.LOGINED) {
                    //登录房间成功。当登录房间或切换房间成功后，进入该状态，表示登录房间已经成功，用户可以正常收到房间内的其他用户和所有流信息增删的回调通知。
                    //只有当房间状态是登录成功或重连成功时，推流（startPublishingStream）、拉流（startPlayingStream）才能正常收发音视频
                } else if(reason == ZegoRoomStateChangedReason.LOGIN_FAILED) {
                    //登录房间失败。当登录房间或切换房间失败后，进入该状态，表示登录房间或切换房间已经失败，例如 AppID 或 Token 不正确等。
                } else if(reason == ZegoRoomStateChangedReason.RECONNECTING) {
                    //房间连接临时中断。如果因为网络质量不佳产生的中断，SDK 会进行内部重试。
                } else if(reason == ZegoRoomStateChangedReason.RECONNECTED) {
                    //房间重新连接成功。如果因为网络质量不佳产生的中断，SDK 会进行内部重试，重连成功后进入该状态。
                } else if(reason == ZegoRoomStateChangedReason.RECONNECT_FAILED) {
                    //房间重新连接失败。如果因为网络质量不佳产生的中断，SDK 会进行内部重试，重连失败后进入该状态。
                } else if(reason == ZegoRoomStateChangedReason.KICK_OUT) {
                    //被服务器踢出房间。例如有相同用户名在其他地方登录房间导致本端被踢出房间，会进入该状态。
                } else if(reason == ZegoRoomStateChangedReason.LOGOUT) {
                    //登出房间成功。没有登录房间前默认为该状态，当调用 [logoutRoom] 登出房间成功或 [switchRoom] 内部登出当前房间成功后，进入该状态。
                } else if(reason == ZegoRoomStateChangedReason.LOGOUT_FAILED) {
                    //登出房间失败。当调用 [logoutRoom] 登出房间失败或 [switchRoom] 内部登出当前房间失败后，进入该状态。
                }
            }

            //用户推送音视频流的状态通知
            //用户推送音视频流的状态发生变更时，会收到该回调。如果网络中断导致推流异常，SDK 在重试推流的同时也会通知状态变化。
            @Override
            public void onPublisherStateUpdate(String streamID, ZegoPublisherState state, int errorCode, JSONObject extendedData) {
                super.onPublisherStateUpdate(streamID, state, errorCode, extendedData);
                if (errorCode != 0) {
                    //推流状态出错
                }
                if (state == ZegoPublisherState.PUBLISHING) {
                    //正在推流中
                } else if (state == ZegoPublisherState.NO_PUBLISH){
                    //未推流
                } else if (state == ZegoPublisherState.PUBLISH_REQUESTING){
                    //正在请求推流中
                }
            }
            //用户拉取音视频流的状态通知
            //用户拉取音视频流的状态发生变更时，会收到该回调。如果网络中断导致拉流异常，SDK 会自动进行重试。
            @Override
            public void onPlayerStateUpdate(String streamID, ZegoPlayerState state, int errorCode, JSONObject extendedData) {
                super.onPlayerStateUpdate(streamID, state, errorCode, extendedData);
                if (errorCode != 0) {
                    //拉流状态出错
                }
                if (state == ZegoPlayerState.PLAYING) {
                    //正在拉流中
                } else if (state == ZegoPlayerState.NO_PLAY){
                    //未拉流
                } else if (state == ZegoPlayerState.PLAY_REQUESTING){
                    //正在请求拉流中
                }
            }

            @Override
            public void onNetworkQuality(String userID, ZegoStreamQualityLevel zegoStreamQualityLevel, ZegoStreamQualityLevel zegoStreamQualityLevel1) {
                super.onNetworkQuality(userID, zegoStreamQualityLevel, zegoStreamQualityLevel1);
                if (userID == null) {
                    // 代表本地用户（我）的网络质量
                    //("我的上行网络质量是 %lu", (unsigned long)upstreamQuality);
                    //("我的下行网络质量是 %lu", (unsigned long)downstreamQuality);
                } else {
                    //代表房间内其他用户的网络质量
                    //("用户 %s 的上行网络质量是 %lu", userID, (unsigned long)upstreamQuality);
                    //("用户 %s 的下行网络质量是 %lu", userID, (unsigned long)downstreamQuality);
                }

                /*
                ZegoStreamQualityLevel.EXCELLENT, 网络质量极好
                ZegoStreamQualityLevel.GOOD, 网络质量好
                ZegoStreamQualityLevel.MEDIUM, 网络质量正常
                ZegoStreamQualityLevel.BAD, 网络质量差
                ZegoStreamQualityLevel.DIE, 网络异常
                ZegoStreamQualityLevel.UNKNOWN, 网络质量未知
                */
            }
        });
    }
}