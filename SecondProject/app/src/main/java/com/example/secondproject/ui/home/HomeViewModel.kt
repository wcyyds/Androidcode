package com.example.secondproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondproject.data.BaseResponse
import com.example.secondproject.data.RetrofitBuilder
import com.example.secondproject.data.RetrofitBuilder.dataConvert
import com.example.secondproject.data.banner.Banner
import com.example.secondproject.data.banner.BannerItem
import com.example.secondproject.data.http.api
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

    var banner = MutableLiveData<Banner>()
    fun getFictions() {
        /*viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题*/
        viewModelScope.launch {
            try {
                /*dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下下可以节省不少无用代码*/
                val data = RetrofitBuilder.creat(api::class.java)
                    .getBanner().dataConvert()

                /*给LiveData赋值  ui会自动更新*/
                banner.value = data

            } catch (e: Exception) {


                /*请求异常的话在这里处理*/
                e.printStackTrace()

                Log.i("请求失败", "${e.message}")

            }


        }
    }


}