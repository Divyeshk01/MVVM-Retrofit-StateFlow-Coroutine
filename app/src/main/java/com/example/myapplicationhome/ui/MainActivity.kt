package com.example.myapplicationhome.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationhome.AppActivity
import com.example.myapplicationhome.R
import com.example.myapplicationhome.data.bean.Status
import kotlinx.coroutines.launch

class MainActivity : AppActivity() {
    private val vm: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
                        Toast.makeText(this@MainActivity,"success:${result.data.toString()}", Toast.LENGTH_LONG).show()
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