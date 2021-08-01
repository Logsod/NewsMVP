package com.local.newsmvp.di.component

import com.local.newsmvp.di.module.NewsModule
import com.local.newsmvp.di.scope.NewsScope
import com.local.newsmvp.ui.news.NewsActivity
import dagger.Component
import javax.inject.Singleton

@NewsScope
@Component(modules = [NewsModule::class], dependencies = [AppCompnent::class])

interface NewsComponent {
    fun inject(activity: NewsActivity)
}