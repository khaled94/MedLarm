package com.example.medlarm.data.model.responseModels

data class UserResponse(
    val Code: String,
    val Msg: String,
    val Status: String,
    val userResponseData: UserResponseData
)