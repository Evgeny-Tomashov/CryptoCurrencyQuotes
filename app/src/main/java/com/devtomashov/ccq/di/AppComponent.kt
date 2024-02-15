package com.devtomashov.ccq.di

import com.devtomashov.ccq.ui.quotes.QuotesFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в QuotesFragmentViewModel
    fun inject(quotesFragmentViewModel: QuotesFragmentViewModel)
}