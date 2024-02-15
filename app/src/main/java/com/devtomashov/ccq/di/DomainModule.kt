package com.devtomashov.ccq.di

import com.devtomashov.ccq.data.MainRepository
import com.devtomashov.ccq.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository) = Interactor(repo = repository)
}