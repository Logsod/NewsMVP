package com.local.newsmvp.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.local.newsmvp.data.local.ArticleDao
import com.local.newsmvp.data.local.LocalDataSource
import com.local.newsmvp.data.remote.NewsRetrofit
import com.local.newsmvp.data.remote.RemoteDataSource
import com.local.newsmvp.data.remote.RemoteService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {


    @Singleton
    @Provides
    fun provideLocalDataSource(): LocalDataSource {
        return Room.databaseBuilder(app, LocalDataSource::class.java, "articlesDb").build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(localDataSource: LocalDataSource): ArticleDao {
        return localDataSource.getArticlesDao()
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideRemoteService(): RemoteService {
        return Retrofit.Builder()
            .baseUrl(NewsRetrofit.REMOTE_ARTICLE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getRetrofitClient())
            .build()
            .create(RemoteService::class.java)
    }

    private fun getRetrofitClient() =
        OkHttpClient.Builder().addInterceptor { chain ->

            val url = chain.request().url().newBuilder()
                .addQueryParameter("apiKey", NewsRetrofit.API_KEY).build()

            val request = chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .url(url)
                .build()
            val response = chain.proceed(request)
            response
        }
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun provideRemoteDataSource(remoteService: RemoteService): RemoteDataSource {
        return RemoteDataSource(remoteService)
    }
}