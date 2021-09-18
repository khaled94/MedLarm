package com.example.medlarm.view.signup

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val user = MutableLiveData<State<UserResponse>>()

    fun signUp(
        Id: Int, Fname: String, Lname: String, Email: String, DateOfBirth: String, Height: Int,
        Weight: Int, Password: String, ConfirmPassword: String, IsHypertension: Boolean, IsDiabetes: Boolean,
        IsHeartDisease: Boolean, IsKidneyDisease: Boolean, IsLiverDisease: Boolean, IsAsthma: Boolean,
        IsChronicObtructivePulmonaryDisease: Boolean, IsArthritis: Boolean, IsOsteoporosis: Boolean,
        IsCancer: Boolean, IsAlzheimer: Boolean, IsOther: Boolean, IsDeleted: Boolean
       ){
        execute(
            loadingConsumer = {
                user.postValue(State.Loading)
            },
            successConsumer = {
                user.postValue(it)
            },
            throwableConsumer = {
                user.postValue(State.Error(exception = errorHandler.getError(it)))
            },
            useCase = repository.signUp(
                SignUpRequest(
                    Id,
                    Fname,
                    Lname,
                    Email,
                    DateOfBirth,
                    Height,
                    Weight,
                    Password,
                    ConfirmPassword,
                    IsHypertension,
                    IsDiabetes,
                    IsHeartDisease,
                    IsKidneyDisease,
                    IsLiverDisease,
                    IsAsthma,
                    IsChronicObtructivePulmonaryDisease,
                    IsArthritis,
                    IsOsteoporosis,
                    IsCancer,
                    IsAlzheimer,
                    IsOther,
                    IsDeleted
                )
            )
        )
    }

}