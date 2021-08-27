package com.example.medlarm.datasource.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.medlarm.datasource.room.AlarmDatabase
import com.example.medlarm.datasource.room.DAOAccess
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AlarmDatabase {
        return Room
            .databaseBuilder(application, AlarmDatabase::class.java, "Demo1.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AlarmDatabase): DAOAccess {
        return database.AlarmDao()
    }
}