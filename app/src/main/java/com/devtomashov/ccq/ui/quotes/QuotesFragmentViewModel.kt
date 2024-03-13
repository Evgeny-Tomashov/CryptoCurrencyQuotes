package com.devtomashov.ccq.ui.quotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtomashov.ccq.App
import com.devtomashov.ccq.domain.Interactor
import com.devtomashov.ccq.data.entity.Quote
import java.util.concurrent.Executors
import javax.inject.Inject

class QuotesFragmentViewModel : ViewModel() {
    val quotesListLiveData: MutableLiveData<List<Quote>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getQuotes()
    }

    private fun getQuotes() {
        interactor.getQuotesFromApi(object : ApiCallback {
            override fun onSuccess(quotes: List<Quote>) {
                quotesListLiveData.postValue(quotes)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    quotesListLiveData.postValue(interactor.getQuotesFromDb())
                }
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(quotes: List<Quote>)
        fun onFailure()
    }
}


