package com.local.newsmvp.ui.news

import com.local.newsmvp.ui.model.Article


interface IArticleItemClickListener {
    fun onClickArticleItem(item: Article)
}