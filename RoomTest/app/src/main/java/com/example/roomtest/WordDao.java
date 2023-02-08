package com.example.roomtest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//这个就是访问数据库操作的接口
//所有的数据库的增删改查的说明都要在这里说明
@Dao
public interface WordDao {

    //增加,这里面是可以传入多个word的值的
    @Insert
    void insertWords(Word... words);

    //修改
    @Update
    void updateWords(Word... words);

    //删除
    @Delete
    void deletWord(Word... words);

    //这里是删除表中的所有的内容
    @Query("DELETE FROM WORD")
    void deleteAllWords();

    //SELECT * FROM WORD查询WORD表中的所有数据
    //ORDER BY ID DESC还要给一个排序的方式并且是降序排列的方式
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    //List<Word> getAllWords();
    LiveData<List<Word>>getAllWordLive();

}
