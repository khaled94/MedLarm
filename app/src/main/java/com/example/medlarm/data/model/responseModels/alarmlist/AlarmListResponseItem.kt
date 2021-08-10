package com.example.medlarm.data.model.responseModels.alarmlist

data class AlarmListResponseItem(
    val Id: Int,
    val MedicineName: String,
    val Date: String,
    val Time: String,
    val FrequentlyIntakeNo: Int,
    val IntakeCount: Int,
    val FrequentlyIntakeType: String,
    val isTaken: Boolean
)