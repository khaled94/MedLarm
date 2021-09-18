package com.example.medlarm.data.model.responseModels.alarmbydate

data class AlarmByDateResponseItem(
    val Id: Int,
    val MedicineName: String,
    val Date: String,
    val Time: String,
    val FrequentlyIntakeNo: Int,
    val IntakeCount: Int,
    val FrequentlyIntakeType: String,
    val isTaken: Boolean,
    var showOptions: Boolean = false,
    var action: String = ""
)