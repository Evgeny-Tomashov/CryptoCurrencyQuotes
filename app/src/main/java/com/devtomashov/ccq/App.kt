package com.devtomashov.ccq

import android.app.Application
import com.devtomashov.ccq.di.AppComponent
import com.devtomashov.ccq.di.DaggerAppComponent
import com.devtomashov.ccq.di.DatabaseModule
import com.devtomashov.ccq.di.DomainModule
import com.devtomashov.ccq.di.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}