package com.example.others.screensharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.others.R;

import org.json.JSONObject;

import im.zego.commontools.logtools.AppLogger;
import im.zego.commontools.logtools.LogView;
import im.zego.commontools.logtools.logLinearLayout;
import im.zego.commontools.uitools.ZegoViewUtil;
import im.zego.keycenter.KeyCenter;
import im.zego.keycenter.UserIDHelper;
import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.callback.IZegoApiCalledEventHandler;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.constants.ZegoPlayerState;
import im.zego.zegoexpress.constants.ZegoPublishChannel;
import im.zego.zegoexpress.constants.ZegoRoomStateChangedReason;
import im.zego.zegoexpress.constants.ZegoScenario;
import im.zego.zegoexpress.constants.ZegoVideoSourceType;
import im.zego.zegoexpress.entity.ZegoCanvas;
import im.zego.zegoexpress.entity.ZegoEngineProfile;
import im.zego.zegoexpress.entity.ZegoUser;
import im.zego.zegoexpress.entity.ZegoVideoConfig;

public class ScreenSharingActivity extends AppCompatActivity {

    TextView userIDText;
    EditText roomIDEdit;
    EditText publishStreamIDEdit;
    Button startScreenCaptureButton;
    EditText playStreamIDEdit;
    Button playButton;
    TextureView playView;
    TextView roomState;
    EditText encodeResolutionWidth;
    EditText encodeResolutionHeight;
    EditText frameRateEdit;
    EditText bitrateEdit;

    String userID;
    String publishStreamID;
    String playStreamID;
    String roomID;
    ZegoExpressEngine engine;
    Long appID;
    String appSign;
    ZegoUser user;
    static MediaProjectionManager mMediaProjectionManager;
    private static final int REQUEST_CODE = 1001;
    private Intent service;
    public static MediaProjection mMediaProjection;
    private static final int DEFAULT_VIDEO_WIDTH = 360;
    private static final int DEFAULT_VIDEO_HEIGHT = 640;

    //Store whether the user is playing the stream
    Boolean isPlay = false;
    //Store whether the user is publishing the stream
    Boolean isPublish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_sharing);
        bindView();
        requestPermission();
        setLogComponent();
        getAppIDAndUserIDAndAppSign();
        setDefaultValue();
        initEngineAndUser();
        prepareScreenCapture();
        setPlayButtonEvent();
        setEventHandler();
        setStartScreenCaptureButtonEvent();
        setApiCalledResult();
    }

    //初始化所有的组件
    public void bindView(){
        userIDText = findViewById(R.id.userID);
        roomIDEdit = findViewById(R.id.roomIDEdit);
        publishStreamIDEdit = findViewById(R.id.publishIDEdit);
        startScreenCaptureButton = findViewById(R.id.screenCaptureButton);
        playStreamIDEdit = findViewById(R.id.editPlayStreamID);
        playButton = findViewById(R.id.playButton);
        playView = findViewById(R.id.playView);
        roomState = findViewById(R.id.roomState);
        encodeResolutionWidth = findViewById(R.id.encodeResolutionWidth);
        encodeResolutionHeight = findViewById(R.id.encodeResolutionHeight);
        frameRateEdit = findViewById(R.id.frameRateEdit);
        bitrateEdit = findViewById(R.id.bitrateEdit);
    }

    //请求权限
    public void requestPermission() {
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

    // Set log component. It includes a pop-up dialog.
    //设置日志组件。它包括一个弹出对话框
    public void setLogComponent(){
        logLinearLayout logHiddenView = findViewById(R.id.logView);
        logHiddenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogView logview = new LogView(getApplicationContext());
                logview.show(getSupportFragmentManager(),null);
            }
        });
    }

    //get appID and userID and appSign
    public void getAppIDAndUserIDAndAppSign(){
        appID = KeyCenter.getInstance().getAppID();
        userID = UserIDHelper.getInstance().getUserID();
        appSign = KeyCenter.getInstance().getAppSign();
    }

    //设置roomID,推流,拉流和userID
    public void setDefaultValue(){
        roomID = "0033";
        publishStreamID = "0033";
        playStreamID = "0033";
        userIDText.setText(userID);
        setTitle(getString(R.string.screen_sharing));
    }

    //创造引擎和不知道撒
    public void initEngineAndUser(){
        // Initialize ZegoExpressEngine
        // 创建引擎，通用场景接入，并注册 self 为 eventHandler 回调
        // 不需要注册回调的话，eventHandler 参数可以传 null，后续可调用 "setEventHandler:" 方法设置回调
        ZegoEngineProfile profile = new ZegoEngineProfile();
        profile.appID = appID;
        profile.appSign = appSign;
        profile.scenario = ZegoScenario.DEFAULT;
        profile.application = getApplication();
        engine = ZegoExpressEngine.createEngine(profile, null);


        AppLogger.getInstance().callApi("Create ZegoExpressEngine");
        //create the user
        user = new ZegoUser(userID);
    }

    //什么什么变为true,改变默认的摄像头推流源为屏幕共享源
    public void  prepareScreenCapture(){
        engine.enableHardwareEncoder(true);
        engine.setVideoSource(ZegoVideoSourceType.ZEGO_VIDEO_SOURCE_SCREEN_CAPTURE, ZegoPublishChannel.AUX);
    }

    //这是开始拉流的按钮
    public void setPlayButtonEvent(){
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the user is playing the stream, this button is used to stop playing. Otherwise, this button is used to start publishing.
                //如果用户正在播放流，则使用此按钮停止播放。否则，此按钮用于开始发布
                if (isPlay){
                    //此时正在播放,点击,然后就停止拉流
                    engine.stopPlayingStream(playStreamID);
                    AppLogger.getInstance().callApi("Stop Playing Stream:%s",playStreamID);
                    playButton.setText(getString(R.string.start_playing));
                    isPlay = false;
                } else {
                    //此时没有播放,点击,然后就开始拉流
                    playStreamID = playStreamIDEdit.getText().toString();
                    engine.startPlayingStream(playStreamID, new ZegoCanvas(playView));
                    playButton.setText(getString(R.string.stop_playing));
                    AppLogger.getInstance().callApi("Start Playing Stream:%s",playStreamID);
                    isPlay = true;
                }
            }
        });
    }

    //这个应该是检测进出人的
    public void setEventHandler(){
        engine.setEventHandler(new IZegoEventHandler() {
            // The callback triggered when the room connection state changes.
            //房间连接状态更改时触发的回调。
            @Override
            public void onRoomStateChanged(String roomID, ZegoRoomStateChangedReason reason, int errorCode, JSONObject extendedData) {
                ZegoViewUtil.UpdateRoomState(roomState, reason);
            }

            // The callback triggered when the state of stream playing changes.
            @Override
            public void onPlayerStateUpdate(String streamID, ZegoPlayerState state, int errorCode, JSONObject extendedData) {
                super.onPlayerStateUpdate(streamID, state, errorCode, extendedData);
                // If the state is PLAYER_STATE_NO_PLAY and the errcode is not 0, it means that stream playing has failed and
                // no more retry will be attempted by the engine. At this point, the failure of stream playing can be indicated
                // on the UI of the App.
                if(errorCode != 0 && state.equals(ZegoPlayerState.NO_PLAY)) {
                    if (isPlay) {
                        playButton.setText(ZegoViewUtil.GetEmojiStringByUnicode(ZegoViewUtil.crossEmoji) + getString(R.string.stop_playing));
                    }
                } else {
                    if (isPlay) {
                        playButton.setText(ZegoViewUtil.GetEmojiStringByUnicode(ZegoViewUtil.checkEmoji) + getString(R.string.stop_playing));
                    }
                }
            }

        });
    }


    //这个是开始屏幕采集的按钮
    public void setStartScreenCaptureButtonEvent(){
        startScreenCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("setStartScreen", "onClick: 有没有进入开始屏幕采集的按钮");
                if (isPublish){
                    //现在是正在推流中,点击,然后就停止推流
                    AppLogger.getInstance().callApi("Stop Publishing Stream: %s",publishStreamID);
                    startScreenCaptureButton.setText(getString(R.string.start_screen_capture));
                    engine.stopScreenCapture();
                    engine.stopPublishingStream(ZegoPublishChannel.AUX);
                    engine.logoutRoom(roomID);
                    isPublish = false;
                } else {
                    //现在没有推流,点击,然后就开始推流
                    Log.d("startscreen", "onClick: 进入到按钮中");
                    setVideoConfig();
                    Log.d("startscreen", "onClick: 视频配置完了");
                    engine.startScreenCapture();
                    Log.d("startscreen", "onClick: 开始屏幕共享完了");

                    loginRoom();
                    Log.d("startscreen", "onClick: 进入到房间了");

                    publishStreamID = publishStreamIDEdit.getText().toString();
                    Log.d("startscreen", "onClick: 获得推流房间号完了");

                    engine.startPublishingStream(publishStreamID, ZegoPublishChannel.AUX);
                    Log.d("startscreen", "onClick: 推流完了");

                    startScreenCaptureButton.setText(getString(R.string.stop_screen_capture));
                    AppLogger.getInstance().callApi("Start Publishing Stream: %s",publishStreamID);
                    isPublish = true;
                }
            }
        });
    }

    public void setVideoConfig() {
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

    //登录房间
    public void loginRoom(){
        roomID = roomIDEdit.getText().toString();
        //login room
        engine.loginRoom(roomID, user);
        AppLogger.getInstance().callApi("LoginRoom: %s",roomID);
    }




    public void setApiCalledResult(){
        // Update log with api called results
        //使用api调用results更新日志
        ZegoExpressEngine.setApiCalledCallback(new IZegoApiCalledEventHandler() {
            @Override
            public void onApiCalledResult(int errorCode, String funcName, String info) {
                super.onApiCalledResult(errorCode, funcName, info);
                if (errorCode == 0){
                    AppLogger.getInstance().success("[%s]:%s", funcName, info);
                } else {
                    AppLogger.getInstance().fail("[%d]%s:%s", errorCode, funcName, info);
                }
            }
        });
    }








    //这个只是一个跳过来的函数,可以不用看
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ScreenSharingActivity.class);
        activity.startActivity(intent);
    }

    //销毁的
    @Override
    protected void onDestroy() {
        ZegoExpressEngine.destroyEngine(null);
        super.onDestroy();
    }
}