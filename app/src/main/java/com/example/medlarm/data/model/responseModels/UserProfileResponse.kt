package com.example.medlarm.data.model.responseModels

data class UserProfileResponse(
    val Id: Int,
    val Fname: String,
    val Lname: String,
    val Email: String,
    val DateOfBirth: String,
    val Height: Double,
    val Weight: Double,
    val Password: String,
    val IsHypertension: Boolean,
    val IsHighCholesterol: Boolean,
    val IsIschemicHeart: Boolean,
    val IsDiabetes: Boolean,
    val IsChronicKidney: Boolean,
    val IsArthritis: Boolean,
    val IsObstructivePulmonary: Boolean,
    val IsAlzheimer: Boolean,
    val IsDepression: Boolean,
    val IsHeartFailure: Boolean,
    val IsDeleted: Boolean
)