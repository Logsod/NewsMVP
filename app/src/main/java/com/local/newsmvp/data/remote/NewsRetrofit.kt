package com.local.newsmvp.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofit {
    companion object {
        const val REMOTE_ARTICLE_URL: String = "https://newsapi.org/v2/"
        //const val REMOTE_ARTICLE_URL : String = "http://192.168.0.101:81/android/"
        const val API_KEY: String = "b1c2b6925ab0431f8f5a4822dd4d54f6"
    }

//    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(REMOTE_ARTICLE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//
//    val remoteService: RemoteService = retrofit.create(RemoteService::class.java)
}