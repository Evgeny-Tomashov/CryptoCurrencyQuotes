package com.devtomashov.ccq.di

import android.content.Context
import androidx.room.Room
import com.devtomashov.ccq.data.MainRepository
import com.devtomashov.ccq.data.dao.QuoteDao
import com.devtomashov.ccq.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideQuoteDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "quote_db"
        ).build().quoteDao()

    @Provides
    @Singleton
    fun provideRepository(quoteDao: QuoteDao) = MainRepository(quoteDao)
}