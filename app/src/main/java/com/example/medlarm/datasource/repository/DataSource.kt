package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.SignInRequestBody
import com.example.medlarm.data.model.requestModels.SignUpRequestBody
import com.example.medlarm.data.model.responseModels.UserResponse
import io.reactivex.Single

interface DataSource {
    fun signUp(signUpRequestBody: SignUpRequestBody) : Single<UserResponse>
    fun login(signInRequestBody: SignInRequestBody): Single<UserResponse>
}