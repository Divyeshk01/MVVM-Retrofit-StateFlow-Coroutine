package com.example.myapplicationhome

import android.app.Application
import com.example.myapplicationhome.data.api.Repository
import com.example.myapplicationhome.data.api.RetrofitInstance
import com.example.myapplicationhome.data.local.DatabaseBuilder
import com.example.myapplicationhome.data.local.DatabaseHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyApplication : Application() {
    var apiRepository: Repository? = null
    var roomRepository: DatabaseHelper? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()
        instance = this@MyApplication
        GlobalScope.launch {
            apiRepository = Repository(RetrofitInstance.api)
            val dao = DatabaseBuilder.getInstance(this@MyApplication).UserDao()
            roomRepository = DatabaseHelper(dao)
        }
    }

    companion object{
        var instance: MyApplication? = null
    }
}