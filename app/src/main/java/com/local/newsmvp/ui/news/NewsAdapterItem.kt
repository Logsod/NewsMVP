package com.local.newsmvp.ui.news

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.local.newsmvp.databinding.RowItemArticleArticlesActivityBinding
import com.xwray.groupie.databinding.BindableItem
import com.local.newsmvp.R
import com.local.newsmvp.ui.model.Article
import java.lang.Exception



class NewsAdapterItem(private val article: Article, private val clickListener: IArticleItemClickListener) :

    BindableItem<RowItemArticleArticlesActivityBinding>() {
    override fun bind(viewBinding: RowItemArticleArticlesActivityBinding, position: Int) {
        viewBinding.textViewTitleRowArticleItem.text = article.title
        viewBinding.textViewDescriptionRowArticleItem.text = article.description

        viewBinding.root.setOnClickListener {
            clickListener.onClickArticleItem(article)
        }
        if (article.image.isNotEmpty()) {
            article.title.log(this.toString())

            Glide.with(viewBinding.root.context)
                .load(article.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewBinding.progressBarImageLoading.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewBinding.progressBarImageLoading.visibility = View.GONE
                        return false
                    }


                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .timeout(4000)
                .into(viewBinding.imageViewImageRowArticleItem)


        }
    }

    override fun getLayout(): Int {
        return R.layout.row_item_article_articles_activity
    }

}