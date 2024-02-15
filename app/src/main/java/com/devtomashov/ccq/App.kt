package com.devtomashov.ccq

import android.app.Application
import com.devtomashov.ccq.di.AppComponent
import com.devtomashov.ccq.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}