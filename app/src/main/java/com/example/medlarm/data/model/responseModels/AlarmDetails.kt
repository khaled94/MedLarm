package com.example.medlarm.data.model.responseModels

data class AlarmDetails(
    val Id: Int,
    val UserId: Int,
    val MedicineId: Int,
    val FreqIntakeNo: Int,
    val FreqIntakeType: Int,
    val DurationNo: Int,
    val DurationType: Int,
    val StartDate: String,
    val StartTime: String
)