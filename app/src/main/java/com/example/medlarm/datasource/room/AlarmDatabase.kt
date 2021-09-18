package com.example.medlarm.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlarmEntity::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao() : AlarmDao

  /*  companion object {

         @Volatile
         private var INSTANCE: AlarmDatabase? = null

         private val LOCK = Any()

         fun getDatabaseClient(context: Context) : AlarmDatabase {

             if (INSTANCE != null) return INSTANCE!!

             synchronized(this) {

                 INSTANCE = Room
                     .databaseBuilder(context, AlarmDatabase::class.java, "ALARM_DATABASE")
                     .fallbackToDestructiveMigration()
                     .build()

                 return INSTANCE!!

             }
         }

    }*/

}