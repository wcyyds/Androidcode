package com.example.secondproject.ui.home.articlepos.RoomArticle

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.secondproject.data.article.Article

@Entity
data class ArticleCollection(var shareuser: String,var articletitle: String,var articleid: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
