package com.example.secondproject.Other

import com.example.secondproject.data.article.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {
    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page")page: Int): Article

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): ArticleService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleService::class.java)
        }
    }
}