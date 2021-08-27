package com.example.medlarm.data.model.responseModels.alarmlist

import java.util.*

data class AlarmListResponseItem(
    val Id: Int,
    val MedicineName: String,
    val Date: Date,
    val Time: Date,
    val FrequentlyIntakeNo: Int,
    val IntakeCount: Int,
    val FrequentlyIntakeType: String,
    val isTaken: Boolean
)