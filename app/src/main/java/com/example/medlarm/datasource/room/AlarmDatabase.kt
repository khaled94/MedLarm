package com.example.medlarm.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlarmTableModel::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun AlarmDao() : DAOAccess

    /* companion object {

         @Volatile
         private var INSTANCE: AlarmDatabase? = null

         private val LOCK = Any()

         fun getDatabaseClient(context: Context) : AlarmDatabase {

             if (INSTANCE != null) return INSTANCE!!

             synchronized(this) {

                 INSTANCE = Room
                     .databaseBuilder(context, AlarmDatabase::class.java, "LOGIN_DATABASE")
                     .fallbackToDestructiveMigration()
                     .build()

                 return INSTANCE!!

             }
         }

    }*/

}