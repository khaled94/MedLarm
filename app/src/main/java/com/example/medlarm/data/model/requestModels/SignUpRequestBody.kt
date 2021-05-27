package com.example.medlarm.data.model.requestModels

data class SignUpRequestBody(
    val DateOfBirth: String,
    val Email: String,
    val Fname: String,
    val Height: Int,
    val Id: Int,
    val IsAlzheimer: Int,
    val IsArthritis: Int,
    val IsChronicKidney: Int,
    val IsDeleted: Int,
    val IsDepression: Int,
    val IsDiabetes: Int,
    val IsHeartFailure: Int,
    val IsHighCholesterol: Int,
    val IsHypertension: Int,
    val IsIschemicHeart: Int,
    val IsObstructivePulmonary: Int,
    val Lname: String,
    val Password: String,
    val Weight: Int
)