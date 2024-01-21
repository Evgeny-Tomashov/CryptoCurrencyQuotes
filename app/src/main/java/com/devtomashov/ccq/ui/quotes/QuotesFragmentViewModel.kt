package com.devtomashov.ccq.ui.quotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtomashov.ccq.App
import com.devtomashov.ccq.domain.Interactor
import com.devtomashov.ccq.domain.Quote
import javax.inject.Inject

class QuotesFragmentViewModel : ViewModel() {
    val quotesListLiveData = MutableLiveData<List<Quote>>()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        val quotes = interactor.getQuotes()
        quotesListLiveData.postValue(quotes)
    }
}


