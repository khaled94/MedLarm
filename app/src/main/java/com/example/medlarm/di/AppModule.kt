package com.example.medlarm.di

import android.app.Application
import android.content.SharedPreferences
import com.example.medlarm.data.Constants.ENCRYPT_KEY
import com.example.medlarm.data.Constants.PREFS_FILE
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesTaskBaseScheduler(): BaseSchedulerProvider = SchedulerProvider()

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return com.securepreferences.SecurePreferences(application, ENCRYPT_KEY, PREFS_FILE)

    }
}