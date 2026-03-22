package com.example.myapplicationhome.data.api

import com.example.myapplicationhome.data.bean.Resource
import com.example.myapplicationhome.data.bean.User
import com.example.myapplicationhome.utils.ApiUtils

class Repository(val apiService: ApiService) {

    suspend fun userInfo(): Resource<List<User>> {
        return try {
            val response = apiService.getDataList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.success(body)
                } else {
                    Resource.warn(null, "Empty response")
                }
            } else {
                val errorMsg = ApiUtils.getAPIError(response.errorBody())
                Resource.error(null, errorMsg)
            }

        } catch (e: Exception) {
            Resource.error(null, e.message ?: "Something went wrong")
        }
    }
}