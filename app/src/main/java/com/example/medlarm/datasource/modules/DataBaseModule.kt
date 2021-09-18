package com.example.medlarm.datasource.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.medlarm.datasource.room.AlarmDatabase
import com.example.medlarm.datasource.room.AlarmDao
//import com.example.medlarm.datasource.room.DataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AlarmDatabase {
        return Room
            .databaseBuilder(application, AlarmDatabase::class.java, "Alarm.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(@NonNull database: AlarmDatabase): AlarmDao {
        return database.alarmDao()
    }

/*    @Provides
    @Singleton
    fun provideDataMapper(@NonNull database: AlarmDatabase): DataMapper {
        return database.dataMapper()
    }*/

}