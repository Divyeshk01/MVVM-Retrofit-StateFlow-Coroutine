package com.example.myapplicationhome.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationhome.AppActivity
import com.example.myapplicationhome.R
import com.example.myapplicationhome.data.bean.Status
import com.example.myapplicationhome.data.local.UserEntity
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
        setContentView(R.layout.activity_main)

        initObserver()
        vm.callUserInfo()
        setupListeners()
        vm.observeUsers()
        adapter = UserAdapter()

        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = adapter
    }
    private fun setupListeners() {

        binding.etSearch.addTextChangedListener {
            vm.setSearch(it.toString())
        }

        binding.btnAsc.setOnClickListener {
            vm.setSort("ASC")
        }

        binding.btnDesc.setOnClickListener {
            vm.setSort("DESC")
        }
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
                        val mapList = result.data?.map { UserEntity(name = it.name, email = it.email, phone = it.phone) }
                        vm.insertData(mapList ?: emptyList())
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
        lifecycleScope.launch {
            vm.obrCallListUser.collect {
                adapter.setData(it)
                Toast.makeText(this@MainActivity, it.toString(),Toast.LENGTH_LONG)
            }
        }
    }

}