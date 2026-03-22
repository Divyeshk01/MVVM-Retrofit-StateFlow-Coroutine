package com.example.myapplicationhome

import android.app.Application
import com.example.myapplicationhome.data.api.Repository
import com.example.myapplicationhome.data.api.RetrofitInstance

class MyApplication : Application() {
    var apiRepository: Repository? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiRepository = Repository(RetrofitInstance.api)
    }

    companion object{
        var instance: MyApplication? = null
    }
}