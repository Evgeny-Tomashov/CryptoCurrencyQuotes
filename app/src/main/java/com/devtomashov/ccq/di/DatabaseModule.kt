package com.devtomashov.ccq.di

import com.devtomashov.ccq.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()
}