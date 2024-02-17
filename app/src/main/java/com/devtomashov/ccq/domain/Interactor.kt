package com.devtomashov.ccq.domain

import com.devtomashov.ccq.data.Assets
import com.devtomashov.ccq.data.Converter
import com.devtomashov.ccq.data.MainRepository
import com.devtomashov.ccq.data.QuoteApi
import com.devtomashov.ccq.ui.quotes.QuotesFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: QuoteApi) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
     fun getQuotesFromApi(callback: QuotesFragmentViewModel.ApiCallback) {
        retrofitService.getQuote().enqueue(object : Callback<Assets> {
            override fun onResponse(
                call: Call<Assets>,
                response: Response<Assets>
            ) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.data))
            }

            override fun onFailure(call: Call<Assets>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}