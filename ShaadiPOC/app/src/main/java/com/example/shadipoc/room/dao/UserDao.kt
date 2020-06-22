package com.example.shadipoc.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shadipoc.data.room.UserData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<UserData>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UserData)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<UserData>>
}