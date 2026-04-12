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
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.loader, null)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(view)

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        window?.apply {
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0f) // no background dim

            val params = attributes
            attributes = params
        }
    }
}
