package com.local.newsmvp.utils

import com.local.newsmvp.data.local.ArticleEntity
import com.local.newsmvp.data.remote.model.RemoteArticles
import com.local.newsmvp.ui.model.Article

class ArticlesMapper {
    fun fromRemoteArticle(remoteArticle: RemoteArticles.Article): Article {
        return Article(
            title = remoteArticle.title ?: "",
            description = remoteArticle.description ?: "",
            image = remoteArticle.urlToImage ?: "",
            url = remoteArticle.url ?: ""
        )
    }

    fun fromRemoteArticles(articles: List<RemoteArticles.Article>): List<Article> {
        return articles.map { fromRemoteArticle(it) }
    }


    fun toEntity(article: Article): ArticleEntity {
        return ArticleEntity(
            title = article.title,
            description = article.description,
            image = article.image,
            url = article.url
        )
    }

    fun fromEntity(entity: ArticleEntity): Article {
        return Article(
            title = entity.title,
            description = entity.description,
            image = entity.image,
            url = entity.url
        )
    }

    fun fromEntityList(entityList: List<ArticleEntity>): List<Article> {
        return entityList.map { fromEntity(it) }
    }

    fun toEntityList(articleList: List<Article>): List<ArticleEntity> {
        return articleList.map { toEntity(it) }
    }


}