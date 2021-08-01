package com.local.newsmvp.ui.news

import com.local.newsmvp.ui.base.IBaseView
import com.local.newsmvp.ui.model.Article

interface NewsContract {
    interface ContractPresenter {
        fun fetchArticles()
        fun getArticlesFromDb()
        fun getArticlesFromApi()
    }


    interface ContractView : IBaseView {
        fun onArticleListReady(articles: List<Article>)
    }
}