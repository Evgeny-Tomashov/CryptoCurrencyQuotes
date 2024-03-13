package com.devtomashov.ccq.data

import com.devtomashov.ccq.data.entity.Assets
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("assets")
    fun getQuote() : Call<Assets>
}