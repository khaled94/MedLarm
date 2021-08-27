package com.example.medlarm.datasource.room

import android.text.format.Time
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Alarm")
data class AlarmTableModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null,

    @ColumnInfo(name = "MedicineName")
    var MedicineName: String,

    @ColumnInfo(name="Date")
    var Date : String,

    @ColumnInfo(name="Time")
    var Time : String,

    @ColumnInfo(name="FrequentlyIntakeNo")
    var FrequentlyIntakeNo : Int,

    @ColumnInfo(name="IntakeCount")
    var IntakeCount : Int,

    @ColumnInfo(name="FrequentlyIntakeType")
    var FrequentlyIntakeType : String,

    @ColumnInfo(name="isTaken")
    var isTaken : Boolean

)