package com.example.myapplicationhome.data.api

import com.example.myapplicationhome.data.bean.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getDataList(): Response<List<User>>
}