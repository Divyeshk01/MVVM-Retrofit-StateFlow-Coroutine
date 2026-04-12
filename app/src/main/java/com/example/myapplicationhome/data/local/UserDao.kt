package com.example.myapplicationhome.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplicationhome.data.bean.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
     fun getAll(): Flow<List<UserEntity>>
  
    @Insert
    suspend fun insertAll(list: List<UserEntity>)
    
    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()
    
}