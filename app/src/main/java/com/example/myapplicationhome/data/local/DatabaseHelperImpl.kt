package com.example.myapplicationhome.data.local

import kotlinx.coroutines.flow.Flow

class DatabaseHelperImpl(private val database: AppDatabase)  {
     suspend fun getDataList(): Flow<List<UserEntity>> = database.UserDao().getAll()
     suspend fun insertAll(list: List<UserEntity>) = database.UserDao().insertAll(list)
     suspend fun deleteAll() = database.UserDao().deleteAll()
}