package com.example.myapplicationhome

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationhome.utils.SpinnerLoader

abstract class AppActivity : AppCompatActivity() {
    private var progressDialog: SpinnerLoader? = null

    //Progress dialog
    protected fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = SpinnerLoader(this)
        }
        progressDialog?.show()
    }

    protected fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }
}
