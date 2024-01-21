package com.devtomashov.ccq.domain

import com.devtomashov.ccq.data.MainRepository

class Interactor (val repo: MainRepository) {
    fun getQuotes(): List<Quote> = repo.quotesDataBase
}