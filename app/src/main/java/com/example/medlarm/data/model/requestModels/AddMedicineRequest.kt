package com.example.medlarm.data.model.requestModels

data class AddMedicineRequest(
    val MedicineId: Int,
    val UserId: Int,
    val FreqIntakeNo: Int,
    val FreqIntakeType: Int,
    val DurationNo: Int,
    val DurationType: Int,
    val StartDate: String,
    val StartTime: String
)