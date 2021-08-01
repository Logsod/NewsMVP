package com.local.newsmvp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(entity: ArticleEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListArticle(entityList : List<ArticleEntity>) : Completable

    @Query(value = "DELETE FROM articles WHERE 1")
    fun deleteAllArticle(): Single<Int>

    @Query(value = "SELECT * FROM articles WHERE 1")
    fun getAllArticles(): Single<List<ArticleEntity>>
}