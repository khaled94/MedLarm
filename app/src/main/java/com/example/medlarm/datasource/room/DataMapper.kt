package com.example.medlarm.datasource.room

import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataMapper {

/*fun AlarmTableModel.toAlarmListResponseItem() = AlarmListResponseItem(
    this.id!!,
    this.MedicineName,
    LocalDate.parse(this.Date, DateTimeFormatter.ISO_DATE),
    this.Time,
    this.FrequentlyIntakeNo,
    this.IntakeCount,
    this.FrequentlyIntakeType,
    this.isTaken
) */

//fun List<AlarmTableModel>.toAlarmListResponseItemList() = this.map { it.toAlarmListResponseItem() }

fun AlarmListResponseItem.toAlarmTableModel() = AlarmTableModel(
    id = this.Id,
    this.MedicineName,
    this.Date.toString(),
    this.Time.toString(),
    this.FrequentlyIntakeNo,
    this.IntakeCount,
    this.FrequentlyIntakeType,
    this.isTaken
)

fun List<AlarmListResponseItem>.toAlarmTableModelList() = this.map { it.toAlarmTableModel() }
}