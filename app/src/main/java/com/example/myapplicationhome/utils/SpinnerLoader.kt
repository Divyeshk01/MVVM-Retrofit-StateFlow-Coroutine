package com.example.myapplicationhome.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout.LayoutParams
import androidx.core.graphics.drawable.toDrawable
import com.example.myapplicationhome.R

@SuppressLint("InflateParams")
class SpinnerLoader(context: Context) : Dialog(context) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.loader,null)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(view)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        window?.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0f) // 0 = no dim
        }
        // 🔥 remove dim ONLY for this dialog
        val params = window?.attributes
        params?.flags = params.flags and
                WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window?.attributes = params
    }

}
