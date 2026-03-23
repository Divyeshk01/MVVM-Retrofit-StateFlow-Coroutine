package com.example.myapplicationhome.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM Entity")
     fun getAll(): Flow<List<UserEntity>>
  
    @Insert
    suspend fun insertAll(user: List<UserEntity>)
    
    @Delete
    suspend fun delete(user: UserEntity)

    @Query("""
        SELECT * FROM Entity
        WHERE name LIKE '%' || :query || '%' 
        OR email LIKE '%' || :query || '%'
        ORDER BY 
            CASE WHEN :sortType = 'ASC' THEN name END ASC,
            CASE WHEN :sortType = 'DESC' THEN name END DESC
    """)
    fun getUsers(query: String, sortType: String): Flow<List<UserEntity>>
    
}