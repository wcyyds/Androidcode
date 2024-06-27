package com.example.secondproject.ui.study.articlepos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.secondproject.data.RetrofitBuilder
import com.example.secondproject.data.article.Article
import com.example.secondproject.data.http.ApiService
import kotlinx.coroutines.flow.Flow


/*
相比于理解，这更多是一种固定的写法。
创建了Repository单例
 */
object Repository {

    // 设置分页加载时每页的数据项数量
    private const val PAGE_SIZE = 50

    //Retrofit客户端，用于与GitHub API进行网络通信
    private val gitHubService =
        RetrofitBuilder.creat(ApiService::class.java)


    //处理异步流数据
    fun getPagingData(): Flow<PagingData<Article>> {
        return Pager(//创建分页数据流
            config = PagingConfig(PAGE_SIZE),//配置分页行为
            pagingSourceFactory = { ArticlePagingSource(gitHubService) }//实现了加载文章数据的逻辑
        ).flow
    }

}
