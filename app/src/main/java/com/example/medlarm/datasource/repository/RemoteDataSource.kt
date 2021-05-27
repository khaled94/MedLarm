package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.SignInRequestBody
import com.example.medlarm.data.model.requestModels.SignUpRequestBody
import com.example.medlarm.data.model.responseModels.UserResponse
import com.example.medlarm.datasource.ServiceApi
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor
    (private val serviceApi: ServiceApi) : DataSource {
    override fun signUp(signUpRequestBody: SignUpRequestBody): Single<UserResponse> {
        return serviceApi.signUp(signUpRequestBody)
    }

    override fun login(signInRequestBody: SignInRequestBody): Single<UserResponse> {
        return serviceApi.login(signInRequestBody)
    }

}