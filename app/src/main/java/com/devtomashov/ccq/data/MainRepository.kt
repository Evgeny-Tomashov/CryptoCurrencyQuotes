package com.devtomashov.ccq.data

import com.devtomashov.ccq.data.dao.QuoteDao
import com.devtomashov.ccq.data.entity.Quote
import java.util.concurrent.Executors

class MainRepository(private val quoteDao: QuoteDao) {

    fun putToDb(quotes: List<Quote>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            quoteDao.insertAll(quotes)
        }
    }

    fun getAllFromDb(): List<Quote> {
        return quoteDao.getCachedQuotes()
    }
}