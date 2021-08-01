package com.local.newsmvp.di.component

import com.local.newsmvp.data.local.LocalDataSource
import com.local.newsmvp.data.remote.RemoteDataSource
import com.local.newsmvp.di.module.AppModule
import com.local.newsmvp.ui.news.NewsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppCompnent {
    fun localDataSource(): LocalDataSource
    fun remoteDataSource(): RemoteDataSource
}