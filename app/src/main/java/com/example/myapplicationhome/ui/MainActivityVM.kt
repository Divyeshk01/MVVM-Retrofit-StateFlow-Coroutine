
package com.example.myapplicationhome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationhome.MyApplication
import com.example.myapplicationhome.data.bean.Resource
import com.example.myapplicationhome.data.bean.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel(){
    val api = MyApplication.instance?.apiRepository

    private val _obrCallUserInfo = MutableStateFlow<Resource<List<User>>>(Resource.loading(null))
    val obrCallUserInfo: StateFlow<Resource<List<User>>> = _obrCallUserInfo


    fun callUserInfo() {
        viewModelScope.launch {
            _obrCallUserInfo.value = Resource.loading(null)
            api?.userInfo()?.let {
                _obrCallUserInfo.value =it
            }
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            try {
             //   val CoursesFromDb = dbHelper.getCourses()
                // here you have your CoursesFromDb
            } catch (e: Exception) {
                // handler error
            }
        }
    }

}