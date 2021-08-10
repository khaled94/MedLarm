package com.example.medlarm.data.model.responseModels.userhistory

data class UserHistoryResponseItem(
    val Id: Int,
    val MedicineName: String,
    val StartDate: String,
    val EndDate: String,
    val FrequentlyIntakeNo: Int,
    val FrequentlyIntakeType: String
)