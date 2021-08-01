package com.local.newsmvp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : IBaseView> : IBasePresenter<V> {
    private var mView: V? = null
    protected var mDisposable: CompositeDisposable? = null
    override fun attachView(view: V) {
        mDisposable = CompositeDisposable()
        mView = view
    }

    override fun detachView() {
        if (mView != null) mView = null
        mDisposable?.dispose()
    }

    override fun getView(): V {
        return mView!!
    }

}