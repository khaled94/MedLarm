package com.example.medlarm.view.editprofile

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.UserProfileResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class EditProfileViewModel@Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val user = MutableLiveData<State<UserResponse>>()
    val userProfile = MutableLiveData<State<UserProfileResponse>>()

    fun editProfile(
        Id: Int, Fname: String, Lname: String, Email: String, DateOfBirth: String, Height: Int,
        Weight: Int, Password: Int, ConfirmPassword: Int, IsHypertension: Boolean,
        IsHighCholesterol: Boolean, IsIschemicHeart: Boolean, IsDiabetes: Boolean, IsChronicKidney: Boolean,
        IsArthritis: Boolean, IsObstructivePulmonary: Boolean, IsAlzheimer: Boolean, IsDepression: Boolean,
        IsHeartFailure: Boolean, IsDeleted: Boolean
    ) {
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
                    IsHighCholesterol,
                    IsIschemicHeart,
                    IsDiabetes,
                    IsChronicKidney,
                    IsArthritis,
                    IsObstructivePulmonary,
                    IsAlzheimer,
                    IsDepression,
                    IsHeartFailure,
                    IsDeleted
                )
            )
        )
    }


    fun getUserDate(userId: Int){
        execute(
            loadingConsumer = {
                userProfile.postValue(State.Loading)
            },
            successConsumer = {
                userProfile.postValue(it)
            },
            throwableConsumer = {
                user.postValue(State.Error(exception = errorHandler.getError(it)))
            },
            useCase = repository.getUserProfile(userId)
        )
    }


}