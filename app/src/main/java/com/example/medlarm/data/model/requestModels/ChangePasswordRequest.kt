package com.example.medlarm.data.model.requestModels

data class ChangePasswordRequest(
    val ConfirmPassword: String,
    val Id: Int,
    val Password: String
)