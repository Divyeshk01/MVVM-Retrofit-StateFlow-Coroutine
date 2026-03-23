package com.example.myapplicationhome.data.local

import kotlinx.coroutines.flow.Flow

class DatabaseHelper(private val dao: UserDao) {

     val usersFlow: Flow<List<UserEntity>> = dao.getAll()

    suspend fun insertAll(userList: List<UserEntity>) =
        dao.insertAll(userList)

    fun getUsers(query: String, sortType: String): Flow<List<UserEntity>> {
        return dao.getUsers(query, sortType)
    }
}