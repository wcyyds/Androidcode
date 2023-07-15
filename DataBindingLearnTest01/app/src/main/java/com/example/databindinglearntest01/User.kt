package com.example.databindinglearntest01

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


class User(): BaseObservable() {
    @get:Bindable
    var userName:String = ""
//        set(value){
//            field = value
//            notifyPropertyChanged(BR.userName)
//        }
}