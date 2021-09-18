package com.example.medlarm.datasource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<AlarmEntity>)

    @Query("SELECT * from Alarms")
    fun getAllAlarms(): List<AlarmEntity>

    @Query("DELETE FROM Alarms")
    fun nukeTable()

}