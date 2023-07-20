package com.example.jackpacktest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updataeUser(newUser : User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age11")
    fun loadUsersOlderThan(age11: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName11")
    fun deleteUserByLastName(lastName11: String): Int

}