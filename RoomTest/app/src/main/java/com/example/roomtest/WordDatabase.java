package com.example.roomtest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//把这个改成单例模式singleton,因为Database实例化是比较消耗资源的,所以不让他出现多个实例
//abstract抽象类,version版本
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;

    static WordDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

}
