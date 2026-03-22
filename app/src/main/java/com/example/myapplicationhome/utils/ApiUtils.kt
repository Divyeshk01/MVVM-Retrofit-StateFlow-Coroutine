package com.example.myapplicationhome.utils

import com.google.gson.Gson
import okhttp3.ResponseBody

object ApiUtils {

    //Convert api error body
    fun getAPIError(errorBody: ResponseBody?): String {
        return try {
            val apiRes: Any = Any()
            val apiResponse: Any? =
                Gson().fromJson(errorBody?.string(), apiRes::class.java)
            apiResponse?.toString() ?: ""
        } catch (e: Exception) {
            errorBody?.string() ?: ""
        }
    }
}