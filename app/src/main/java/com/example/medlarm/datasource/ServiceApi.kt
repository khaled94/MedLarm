package com.example.medlarm.datasource

import com.example.medlarm.data.model.requestModels.SignInRequestBody
import com.example.medlarm.data.model.requestModels.SignUpRequestBody
import com.example.medlarm.data.model.responseModels.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("/PostSignUp")
    fun signUp(@Body body: SignUpRequestBody): Single<UserResponse>

    @POST("/PostSignIn")
    fun login(@Body body: SignInRequestBody): Single<UserResponse>
}