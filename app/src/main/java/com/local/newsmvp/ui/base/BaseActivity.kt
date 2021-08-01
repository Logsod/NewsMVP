package com.local.newsmvp.ui.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {

        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .show()

    }
    protected abstract fun setUp()


}