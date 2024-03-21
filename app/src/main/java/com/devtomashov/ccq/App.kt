package com.devtomashov.ccq

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.devtomashov.ccq.di.AppComponent
import com.devtomashov.ccq.di.DaggerAppComponent
import com.devtomashov.ccq.di.DatabaseModule
import com.devtomashov.ccq.di.DomainModule
import com.devtomashov.ccq.di.RemoteModule
import com.devtomashov.ccq.ui.notifications.NotificationConstants.CHANNEL_ID

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Задаем имя, описание и важность канала
            val name = "TradeLaterChannel"
            val descriptionText = "Quotes notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            //Создаем канал, передав в параметры его ID(строка), имя(строка), важность(константа)
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            //Отдельно задаем описание
            mChannel.description = descriptionText
            //Получаем доступ к менеджеру нотификаций
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            //Регистрируем канал
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}