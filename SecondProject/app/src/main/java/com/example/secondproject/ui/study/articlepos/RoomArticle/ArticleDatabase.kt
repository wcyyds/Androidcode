package com.example.secondproject.ui.study.articlepos.RoomArticle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//room创建
@Database(version = 1, entities = [ArticleCollection::class])
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleCollectionDao(): ArticleCollectionDao

    companion object {

        private var instance: ArticleDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): ArticleDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java, "app_articlecollection"
            )
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }

    }

}