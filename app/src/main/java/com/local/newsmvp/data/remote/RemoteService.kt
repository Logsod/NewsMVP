package com.local.newsmvp.data.remote

import com.local.newsmvp.data.remote.model.RemoteArticles
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query


interface RemoteService {
    @GET("top-headlines?")
    //@GET("index.php")
    fun getArticlesFromApi(
        @Query("country") Country: String,
        @HeaderMap headers: Map<String, String>
    ): Flowable<Response<RemoteArticles>>
}
