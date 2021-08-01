package com.local.newsmvp.data.remote.model

data class RemoteArticles(
    val articles: List<Article> = listOf(),
    val status: String = "",
    val totalResults: Int = 0
) {
    data class Article(
        val author: Any? = Any(),
        val content: Any? = Any(),
        val description: String? = "",
        val publishedAt: String? = "",
        val source: Source? = Source(),
        val title: String? = "",
        val url: String? = "",
        val urlToImage: String? = ""
    ) {
        data class Source(
            val id: Any = Any(),
            val name: String = ""
        )
    }
}