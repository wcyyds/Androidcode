package com.example.secondproject.data.http

import android.telecom.Call
import com.example.secondproject.data.BaseResponse
import com.example.secondproject.data.banner.Banner
import com.example.secondproject.data.banner.BannerItem

import retrofit2.http.GET

interface api{

    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<Banner>


}