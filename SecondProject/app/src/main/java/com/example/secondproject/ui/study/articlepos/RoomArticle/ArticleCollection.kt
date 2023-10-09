package com.example.secondproject.ui.study.articlepos.RoomArticle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleCollection(var shareuser: String,var articletitle: String,var articleid: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
