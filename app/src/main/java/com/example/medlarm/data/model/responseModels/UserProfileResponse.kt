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
    val IsDiabetes: Boolean,
    val IsHeartDisease: Boolean,
    val IsKidneyDisease: Boolean,
    val IsLiverDisease: Boolean,
    val IsAsthma: Boolean,
    val IsChronicObtructivePulmonaryDisease: Boolean,
    val IsArthritis: Boolean,
    val IsOsteoporosis: Boolean,
    val IsCancer: Boolean,
    val IsAlzheimer: Boolean,
    val IsDepression: Boolean,
    val haveChronicDisease: Boolean,
    val IsOther: Boolean,
    val IsDeleted: Boolean
)