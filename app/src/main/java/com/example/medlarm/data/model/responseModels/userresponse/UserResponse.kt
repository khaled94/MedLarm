package com.example.medlarm.data.model.responseModels.userresponse

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val Code: String,
    val Msg: String,
    val Status: String,
    @SerializedName("data")
    val userResponseData: UserResponseData
)