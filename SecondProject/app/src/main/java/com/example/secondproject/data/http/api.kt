package com.example.secondproject.data.http

import com.example.secondproject.data.BaseResponse
import com.example.secondproject.data.article.Articleslist
import com.example.secondproject.data.banner.Banner

import retrofit2.http.GET
import retrofit2.http.Path

interface api{

    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<Banner>

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page")page: Int): BaseResponse<Articleslist>


}