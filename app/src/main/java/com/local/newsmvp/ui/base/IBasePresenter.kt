package com.local.newsmvp.ui.base

interface IBasePresenter <V : IBaseView> {
    fun attachView(view : V)
    fun detachView()
    fun getView() : V
}