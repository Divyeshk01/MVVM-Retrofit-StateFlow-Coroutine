@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.myapplicationhome.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationhome.MyApplication
import com.example.myapplicationhome.data.bean.Resource
import com.example.myapplicationhome.data.bean.User
import com.example.myapplicationhome.data.local.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel(){
    val api = MyApplication.instance?.apiRepository
    val room = MyApplication.instance?.roomRepository
    private val searchQuery = MutableStateFlow("")
    private val sortType = MutableStateFlow("ASC")
    private val _obrCallUserInfo = MutableStateFlow<Resource<List<User>>>(Resource.loading(null))
    val obrCallUserInfo: StateFlow<Resource<List<User>>> = _obrCallUserInfo

    private val _obrCallListUser = MutableStateFlow<List<UserEntity>>(emptyList())
    val obrCallListUser: StateFlow<List<UserEntity>> = _obrCallListUser
    fun callUserInfo() {
        viewModelScope.launch {
            _obrCallUserInfo.value = Resource.loading(null)
            api?.userInfo()?.let {
                _obrCallUserInfo.value =it
            }
        }
    }
    val users: StateFlow<List<UserEntity>> =
        combine(searchQuery, sortType) { query, sort ->
            query to sort
        }.flatMapLatest { (query, sort) ->
            room?.getUsers(query, sort) ?: flowOf(emptyList())
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // 🔍 Update search
    fun setSearch(query: String) {
        searchQuery.value = query
    }

    // ⬆️⬇️ Update sort
    fun setSort(sort: String) {
        sortType.value = sort
    }
    fun insertData(list:List<UserEntity>){
        viewModelScope.launch {
            room?.insertAll(list)
        }
    }

     fun observeUsers() {
        viewModelScope.launch {
            room?.usersFlow?.collect {
                _obrCallListUser.value = it
            }
        }
    }
}