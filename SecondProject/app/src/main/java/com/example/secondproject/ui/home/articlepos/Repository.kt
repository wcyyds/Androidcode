package com.example.secondproject.ui.home.articlepos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.secondproject.data.RetrofitBuilder
import com.example.secondproject.data.RetrofitBuilder.dataConvert
import com.example.secondproject.data.article.Article
import com.example.secondproject.data.article.Articleslist
import com.example.secondproject.data.http.ApiService
import kotlinx.coroutines.flow.Flow


/*
相比于理解，这更多是一种固定的写法。
 */
object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubService =
        RetrofitBuilder.creat(ApiService::class.java)


    fun getPagingData(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { ArticlePagingSource(gitHubService) }
        ).flow
    }

}
