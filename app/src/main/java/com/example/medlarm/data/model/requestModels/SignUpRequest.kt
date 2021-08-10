package com.example.medlarm.data.model.requestModels

data class SignUpRequest(
    val Id: Int,
    val Fname: String,
    val Lname: String,
    val Email: String,
    val DateOfBirth: String,
    val Height: Int,
    val Weight: Int,
    val Password: Int,
    val ConfirmPassword: Int,
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