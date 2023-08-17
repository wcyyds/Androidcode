package com.example.secondproject.ui.home.articlepos.RoomArticle

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.secondproject.data.article.Article

@Dao
interface ArticleCollectionDao {

    @Insert
    fun insertArticle(articleCollection: ArticleCollection): Long

    @Query("delete from ArticleCollection where articleid = :articleid")
    fun deleteArticle(articleid : Int): Int

    @Query("select count(*) from ArticleCollection where articleid = :articleid")
    fun querArticle(articleid: Int) : Int

    @Query("select * from ArticleCollection")
    fun loadAllArticleCollection(): List<ArticleCollection>

}