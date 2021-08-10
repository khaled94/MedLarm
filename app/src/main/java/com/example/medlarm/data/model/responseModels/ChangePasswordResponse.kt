package com.example.medlarm.data.model.responseModels

data class ChangePasswordResponse(
    val Code: String,
    val Status: String,
    val Msg: String,
    val data: Int
)