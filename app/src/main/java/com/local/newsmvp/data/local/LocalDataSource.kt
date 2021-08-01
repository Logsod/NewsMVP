package com.local.newsmvp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ArticleEntity::class], version = 1
)
abstract class LocalDataSource : RoomDatabase() {
    abstract fun getArticlesDao() : ArticleDao
//    companion object {
//        var INSTANCE: LocalDataSource? = null
//
//        fun getRoomBookDatabase(context: Context): LocalDataSource? {
//            if (INSTANCE == null) {
//                synchronized(LocalDataSource::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        LocalDataSource::class.java,
//                        "articlesDb"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyDataBase() {
//            INSTANCE = null
//        }
//    }
}