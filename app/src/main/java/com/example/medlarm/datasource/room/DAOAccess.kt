package com.example.medlarm.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<AlarmTableModel>)

    @Query("SELECT * from Alarm")
    fun queryData(): Single<List<AlarmTableModel>>
}