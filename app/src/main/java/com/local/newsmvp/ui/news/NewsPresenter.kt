package com.local.newsmvp.ui.news

import com.local.newsmvp.ui.base.BasePresenter
import com.local.newsmvp.data.local.LocalDataSource
import com.local.newsmvp.data.remote.RemoteDataSource
import com.local.newsmvp.ui.model.Article
import com.local.newsmvp.utils.ArticlesMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource

) : BasePresenter<NewsContract.ContractView>(),
    NewsContract.ContractPresenter {

    //val service = NewsRetrofit().remoteService
    val articlesMapper = ArticlesMapper()

    //val localDataSource: LocalDataSource = LocalDataSource.getRoomBookDatabase(context)!!
    override fun fetchArticles() {
        getArticlesFromDb()
        getArticlesFromApi()
    }

    override fun getArticlesFromDb() {
        val d = localDataSource.getArticlesDao().getAllArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView().onArticleListReady(articlesMapper.fromEntityList(it))
            },
                {

                })
        mDisposable?.add(d)
    }

    private fun clearDb() =
        localDataSource.getArticlesDao().deleteAllArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun addArticleListToDb(articles: List<Article>) {
        val d = clearDb().subscribe(
            {
                mDisposable?.add(
                    localDataSource.getArticlesDao()
                        .insertListArticle(articlesMapper.toEntityList(articles))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({}, {})
                )
            }, {}
        )

        mDisposable?.add(d)
    }

    override fun getArticlesFromApi() {
        val d = remoteDataSource.getArticlesFromApi("ru")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.delaySubscription(5, TimeUnit.SECONDS)
            .subscribe(
                {
                    if (it.isSuccessful) {
                        val articles = articlesMapper.fromRemoteArticles(it.body()!!.articles)
                        addArticleListToDb(articles)
                        getView().onArticleListReady(articles)
                    }
                },
                {
                    getView().showError(it.message ?: "error receive remote news")
                }
            )
        mDisposable?.add(d)
    }

}