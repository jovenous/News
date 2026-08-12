package com.veylik.news.di

import android.content.Context
import androidx.room.Room
import com.veylik.news.data.local.NewsDao
import com.veylik.news.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideNewsDatabase(
            @ApplicationContext context: Context
        ): NewsDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = NewsDatabase::class.java,
                name = "news.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()
        }

        @Singleton
        @Provides
        fun provideNewsDao(
            newsDatabase: NewsDatabase
        ): NewsDao {
            return newsDatabase.newsDao()
        }
    }
}