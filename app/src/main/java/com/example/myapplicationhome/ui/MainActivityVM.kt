package com.example.myapplicationhome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationhome.MyApplication
import com.example.myapplicationhome.data.bean.Resource
import com.example.myapplicationhome.data.bean.User
import com.example.myapplicationhome.data.local.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel(){
    val api = MyApplication.instance?.apiRepository
    val roomRepo = MyApplication.instance?.roomRepository

    private val _obrCallUserInfo = MutableStateFlow<Resource<List<User>>>(Resource.loading(null))
    val obrCallUserInfo: StateFlow<Resource<List<User>>> = _obrCallUserInfo

    private val _obrLocalDataInfo = MutableStateFlow<List<UserEntity>>(emptyList())
    val obrLocalDataInfo: StateFlow<List<UserEntity>> = _obrLocalDataInfo
    fun callUserInfo() {
        viewModelScope.launch {
            _obrCallUserInfo.value = Resource.loading(null)
            api?.userInfo()?.let {
                _obrCallUserInfo.value =it
            }
        }
    }

    fun insertList(list: List<UserEntity>) {
        viewModelScope.launch {
            roomRepo?.deleteAll()
            roomRepo?.insertAll(list)
        }
    }

     fun fetchListData() {
        viewModelScope.launch {
            try {
                roomRepo?.getDataList()?.collect{
                    _obrLocalDataInfo.value = it
                }
            } catch (e: Exception) {
                // handler error
            }
        }
    }

}