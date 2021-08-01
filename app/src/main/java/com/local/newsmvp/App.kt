package com.local.newsmvp

import android.app.Application
import com.local.newsmvp.di.component.AppCompnent
import com.local.newsmvp.di.component.DaggerAppCompnent
import com.local.newsmvp.di.component.DaggerNewsComponent
import com.local.newsmvp.di.component.NewsComponent
import com.local.newsmvp.di.module.AppModule
import com.local.newsmvp.di.module.NewsModule

class App : Application() {
    lateinit var appComponent: AppCompnent
    lateinit var newsComponent: NewsComponent
    override fun onCreate() {
        appComponent = DaggerAppCompnent.builder().appModule(AppModule(this)).build()
        newsComponent = DaggerNewsComponent.builder().appCompnent(appComponent).build()

        super.onCreate()
    }
}