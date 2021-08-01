package com.local.newsmvp.data.remote

class RemoteDataSource(private val remoteService: RemoteService) {
    fun getArticlesFromApi(
        country: String,
        headers: Map<String, String> = HashMap<String, String>()
    ) =
        remoteService.getArticlesFromApi(country, headers)
}