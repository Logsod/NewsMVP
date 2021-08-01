package com.local.newsmvp.ui.base

import android.content.Context

interface IBaseView {
    fun showToast(context: Context, message : String)
    fun showError(message: String)
}