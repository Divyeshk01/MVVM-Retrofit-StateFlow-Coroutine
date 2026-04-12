package com.example.myapplicationhome

import android.app.Application
import androidx.room.RoomDatabase
import com.example.myapplicationhome.data.api.Repository
import com.example.myapplicationhome.data.api.RetrofitInstance
import com.example.myapplicationhome.data.local.AppDatabase
import com.example.myapplicationhome.data.local.DatabaseBuilder
import com.example.myapplicationhome.data.local.DatabaseHelperImpl

class MyApplication : Application() {
    var apiRepository: Repository? = null
    var roomRepository: DatabaseHelperImpl? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiRepository = Repository(RetrofitInstance.api)
        roomRepository =DatabaseHelperImpl(DatabaseBuilder.getInstance(this))
    }

    companion object{
        var instance: MyApplication? = null
    }
}