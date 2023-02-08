package com.example.roomtest02;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person...Persons);

    @Delete
    void deleteById(Person...Persons);

    //使用主键将传递的实实例月数据库中的行进行匹配
    @Update
    void updateById(Person...Persons);

    //查询你想查询的性别
    @Query("select * from person where sex = :sex")
    List<Person> querySex(String sex);

    //查询你想查询的姓名
    @Query("select * from person where name = :name")
    List<Person> queryName(String name);

    //按照姓名排序
    @Query("select * from person order by name desc")
    LiveData<List<Person>> getAllperson();

    //计算人的个数
    //count(*)表示计算总行数,括号中写*或者列名都是可以的
    @Query("select count(*) from person")
    int allPersonNumber();

    //计算年龄的平均数
    @Query("select avg(age) from person")
    int allPersonAgeAvg();


}
