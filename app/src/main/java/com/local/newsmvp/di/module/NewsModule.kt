package com.local.newsmvp.di.module

import com.local.newsmvp.data.local.LocalDataSource
import com.local.newsmvp.data.remote.RemoteDataSource
import com.local.newsmvp.di.scope.NewsScope
import com.local.newsmvp.ui.news.NewsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun provideNewsPresenter(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): NewsPresenter {
        return NewsPresenter(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
    }
}