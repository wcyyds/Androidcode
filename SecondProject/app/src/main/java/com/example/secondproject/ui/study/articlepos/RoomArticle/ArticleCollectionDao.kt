package com.example.secondproject.ui.study.articlepos.RoomArticle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

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