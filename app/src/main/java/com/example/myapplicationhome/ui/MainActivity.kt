package com.example.myapplicationhome.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationhome.AppActivity
import com.example.myapplicationhome.R
import com.example.myapplicationhome.data.bean.Status
import com.example.myapplicationhome.databinding.ActivityMainBinding
import com.example.myapplicationhome.di.UserAdapter
import kotlinx.coroutines.launch

class MainActivity : AppActivity() {
    private val vm: MainActivityVM by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()

        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = adapter
        initObserver()
        vm.callUserInfo()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            vm.obrCallUserInfo.collect { result ->
                when (result.status) {
                    Status.LOADING -> {
                        showProgressDialog()
                    }

                    Status.SUCCESS -> {
                        dismissProgressDialog()
                        Toast.makeText(this@MainActivity,"sucess ${result.data.toString()} ", Toast.LENGTH_LONG).show()
                        adapter.setData(result.data ?: emptyList())

                    }

                    Status.ERROR -> {
                        dismissProgressDialog()
                        Toast.makeText(this@MainActivity,"error:${result.message}", Toast.LENGTH_LONG).show()
                    }

                    Status.WARN -> {
                        dismissProgressDialog()
                        Toast.makeText(this@MainActivity,"warn:${result.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

}