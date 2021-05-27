package com.example.medlarm.di

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

}