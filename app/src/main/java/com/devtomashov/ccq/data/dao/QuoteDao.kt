package com.devtomashov.ccq.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devtomashov.ccq.data.entity.Quote

@Dao
interface QuoteDao {
    //Запрос на всю таблицу
    @Query("SELECT * FROM cached_quotes")
    fun getCachedQuotes(): List<Quote>

    //Кладём списком в БД, в случае конфликта перезаписываем
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Quote>)
}