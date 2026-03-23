package com.example.myapplicationhome

import android.app.Application
import com.example.myapplicationhome.data.api.Repository
import com.example.myapplicationhome.data.api.RetrofitInstance
import com.example.myapplicationhome.data.local.DatabaseBuilder
import com.example.myapplicationhome.data.local.DatabaseHelper

class MyApplication : Application() {
    var apiRepository: Repository? = null
    var roomRepository: DatabaseHelper? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiRepository = Repository(RetrofitInstance.api)
        val dao = DatabaseBuilder.getInstance(this).UserDao()
        roomRepository = DatabaseHelper(dao)
    }

    companion object{
        var instance: MyApplication? = null
    }
}