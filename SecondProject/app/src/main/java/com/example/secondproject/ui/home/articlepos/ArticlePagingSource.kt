package com.example.secondproject.ui.home.articlepos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.secondproject.data.RetrofitBuilder.dataConvert
import com.example.secondproject.data.article.Article
import com.example.secondproject.data.http.ApiService


/*
继承PagingSource时需要声明两个泛型类型，
第一个类型表示页数的数据类型，我们没有特殊需求，所以直接用整型就可以了。
第二个类型表示每一项数据（注意不是每一页）所对应的对象类型

load()函数当中，先通过params参数得到key，这个key就是代表着当前的页数
注意key是可能为null的，如果为null的话，我们就默认将当前页数设置为第0页

最后需要调用LoadResult.Page()函数
第一个参数传入从响应数据解析出来的Repo列表即可
第二和第三个参数分别对应着上一页和下一页的页数。
针对于上一页和下一页，我们还额外做了个判断，如果当前页已经是第一页或最后一页，那么它的上一页或下一页就为null
 */
class ArticlePagingSource(private val apiService: ApiService): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val articleResponse = apiService.getArticle(page).dataConvert()
            val repoItems = articleResponse.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}