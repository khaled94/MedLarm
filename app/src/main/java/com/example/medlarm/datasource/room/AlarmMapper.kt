package com.example.medlarm.datasource.room

import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem


class AlarmMapper {

    fun mapFromEntityToModel(entity: AlarmEntity): AlarmListResponseItem {
         return AlarmListResponseItem(
             entity.id!!,
             entity.MedicineName,
             entity.Date,
             entity.Time,
             entity.FrequentlyIntakeNo,
             entity.IntakeCount,
             entity.FrequentlyIntakeType,
             entity.isTaken
         )
    }

    //fun List<AlarmEntity>.toAlarmListResponseItemList() = this.map { it.toAlarmListResponseItem() }

    fun mapFromModelToEntity(model: AlarmListResponseItem): AlarmEntity {
        return AlarmEntity(
            model.Id,
            model.MedicineName,
            model.Date,
            model.Time,
            model.FrequentlyIntakeNo,
            model.IntakeCount,
            model.FrequentlyIntakeType,
            model.isTaken
        )
    }


    //fun List<AlarmListResponseItem>.toAlarmTableModelList() = this.map { it.toAlarmTableModel() }
}

