package com.example.medlarm.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.medlarm.BuildConfig
import com.example.medlarm.data.Constants.CHANNEL_ID
import com.example.medlarm.di.DaggerAppComponent
import com.example.medlarm.utils.LocaleManager
import com.example.medlarm.utils.PreferenceManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class MedLarm : DaggerApplication() {

    private var currentLocaleContext: Context? = null
    val preferenceManager : PreferenceManager by lazy {
        PreferenceManager (this)
    }
    lateinit var notificationManager: NotificationManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        createNotificationChannel()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.getLocalizedContext(base, PreferenceManager(base).getCurrentLocale()))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }
}