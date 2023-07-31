package com.example.jackpacktest

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MyObserver: DefaultLifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart(){
        Log.d("111MyObserver", "ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){
        Log.d("111MyObserver", "ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityCreat(){
        Log.d("111MyObserver", "ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun activityDestory(){
        Log.d("111MyObserver", "ON_DESTROY")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPause(){
        Log.d("111MyObserver", "ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun activityAny(){
        Log.d("111MyObserver", "ON_ANY1111111")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activity(){
        Log.d("111MyObserver", "ON_RESUME")
    }


//    override fun onStart(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onStart")
//    }
//
//    override fun onStop(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onStop")
//    }
//
//    override fun onCreate(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onCreate")
//    }
//
//    override fun onDestroy(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onDestroy")
//    }
//
//    override fun onPause(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onPause")
//    }
//
//    override fun onResume(owner: LifecycleOwner) {
//        Log.d("MyObserver", "onResume")
//    }



}