package com.devtomashov.ccq.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devtomashov.ccq.data.dao.QuoteDao
import com.devtomashov.ccq.data.entity.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}