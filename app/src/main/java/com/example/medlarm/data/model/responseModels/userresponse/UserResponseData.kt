package com.example.medlarm.data.model.responseModels.userresponse

data class UserResponseData(
    val DateOfBirth: String,
    val Email: String,
    val Fname: String,
    val Height: Double,
    val Id: Int,
    val IsAlzheimer: Boolean,
    val IsArthritis: Boolean,
    val IsChronicKidney: Boolean,
    val IsDeleted: Boolean,
    val IsDepression: Boolean,
    val IsDiabetes: Boolean,
    val IsHeartFailure: Boolean,
    val IsHighCholesterol: Boolean,
    val IsHypertension: Boolean,
    val IsIschemicHeart: Boolean,
    val IsObstructivePulmonary: Boolean,
    val Lname: String,
    val Password: String,
    val Weight: Double
)