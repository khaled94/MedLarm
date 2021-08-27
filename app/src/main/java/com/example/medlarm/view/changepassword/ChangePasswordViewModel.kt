package com.example.medlarm.view.changepassword

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.ChangePasswordRequest
import com.example.medlarm.data.model.responseModels.ChangePasswordResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class ChangePasswordViewModel@Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val newPasswordResponse = MutableLiveData<State<ChangePasswordResponse>>()

    fun changePassword(userId: Int, password: String) {
        execute(loadingConsumer = {
            newPasswordResponse.postValue(State.Loading)
        }, successConsumer = {
            newPasswordResponse.postValue(it)
        }, throwableConsumer = {
            newPasswordResponse.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.changePassword(ChangePasswordRequest(userId,password)))
    }

}