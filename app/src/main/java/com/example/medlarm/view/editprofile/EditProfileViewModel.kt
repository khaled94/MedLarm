package com.example.medlarm.view.editprofile

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.EditProfileRequest
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
        Id: Int, Fname: String, Lname: String, Email: String, DateOfBirth: String, Height: Double,
        Weight: Double, Password: String, IsHypertension: Boolean, IsDiabetes: Boolean,
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
            useCase = repository.updateProfile(
                EditProfileRequest(
                    Id,
                    Fname,
                    Lname,
                    Email,
                    DateOfBirth,
                    Height,
                    Weight,
                    Password,
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