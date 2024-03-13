package com.devtomashov.ccq.di

import android.content.Context
import com.devtomashov.ccq.data.MainRepository
import com.devtomashov.ccq.data.QuoteApi
import com.devtomashov.ccq.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule(val context: Context) {
    //Нам нужно контекст как-то провайдить, поэтому создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideInteractor(
        repository: MainRepository, quoteApi: QuoteApi) =
        Interactor(repo = repository, retrofitService = quoteApi)
}