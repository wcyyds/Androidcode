package com.example.secondproject.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.secondproject.LogUtil
import com.example.secondproject.data.RetrofitBuilder
import com.example.secondproject.data.RetrofitBuilder.dataConvert
import com.example.secondproject.data.article.Article
import com.example.secondproject.data.article.Articleslist
import com.example.secondproject.data.banner.Banner
import com.example.secondproject.data.http.ApiService
import com.example.secondproject.ui.home.articlepos.Repository
import com.example.secondproject.ui.home.articlepos.RoomArticle.ArticleCollection
import com.example.secondproject.ui.home.articlepos.RoomArticle.ArticleCollectionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var banner = MutableLiveData<Banner>()
    fun getBanners() {
        /*viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题*/
        viewModelScope.launch {
            try {
                /*dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下下可以节省不少无用代码*/
                var bannerdata = RetrofitBuilder.creat(ApiService::class.java)
                    .getBanner().dataConvert()
                /*给LiveData赋值  ui会自动更新*/
                automaticRotation(bannerdata)
                banner.value = bannerdata
            } catch (e: Exception) {
                /*请求异常的话在这里处理*/
                e.printStackTrace()

                Log.i("banner请求失败", "${e.message}")
            }
        }
    }

    fun automaticRotation(banner: Banner): Banner {
        var lastNode = banner.last()
        var firstNode = banner.first()
        banner.add(0, lastNode)
        banner.add(banner.size, firstNode)
        return banner
    }

    var article = MutableLiveData<Articleslist>()
    fun getArticles() {
        /*viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题*/
        viewModelScope.launch {
            try {
                /*dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下下可以节省不少无用代码*/
                var articledata = RetrofitBuilder.creat(ApiService::class.java)
                    .getArticle(0).dataConvert()
                /*给LiveData赋值  ui会自动更新*/
                article.value = articledata
            } catch (e: Exception) {
                /*请求异常的话在这里处理*/
                e.printStackTrace()

                Log.i("article请求失败", "${e.message}")
            }
        }
    }

    fun getPagingData(): Flow<PagingData<Article>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
        //cachedIn()函数，这是用于将服务器返回的数据在viewModelScope这个作用域内进行缓存
    }

}



