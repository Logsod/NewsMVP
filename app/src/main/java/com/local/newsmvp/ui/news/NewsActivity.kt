package com.local.newsmvp.ui.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.local.newsmvp.App
import com.local.newsmvp.R
import com.local.newsmvp.databinding.ActivityArticlesBinding
import com.local.newsmvp.ui.base.BaseActivity
import com.local.newsmvp.data.local.LocalDataSource
import com.local.newsmvp.data.remote.NewsRetrofit
import com.local.newsmvp.data.remote.RemoteDataSource
import com.local.newsmvp.ui.model.Article
import com.xwray.groupie.GroupieAdapter
import javax.inject.Inject

class NewsActivity : BaseActivity(), NewsContract.ContractView, IArticleItemClickListener {


    @Inject
    lateinit var mPresenter: NewsPresenter

    private val articleListAdapter = GroupieAdapter()

//    @Inject
//    lateinit var localDataSource: LocalDataSource
//
//    @Inject
//    lateinit var remoteDataSource: RemoteDataSource

    lateinit var binding: ActivityArticlesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_articles)

        (application as App).newsComponent.inject(this)

//        mPresenter = NewsPresenter(
//            localDataSource,
//            remoteDataSource
//            //LocalDataSource.getRoomBookDatabase(this)!!,
//            //RemoteDataSource(NewsRetrofit())
//        )

        mPresenter.attachView(this)

        setUp()
        mPresenter.fetchArticles()
    }


    override fun setUp() {

        supportActionBar?.hide()
        binding.recyclerViewArticleList.adapter = articleListAdapter
        binding.recyclerViewArticleList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onArticleListReady(articles: List<Article>) {
        articleListAdapter.clear()
        articles.forEach {
            articleListAdapter.add(NewsAdapterItem(it, this))
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun onClickArticleItem(item: Article) {
        val uri: Uri? = Uri.parse(item.url)
        try {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        } catch (e: Exception) {
            showError(e.message ?: "error open url")
        }
    }
}