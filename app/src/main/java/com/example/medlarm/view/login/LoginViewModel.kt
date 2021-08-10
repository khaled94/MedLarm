package com.example.medlarm.view.login

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class LoginViewModel@Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val user = MutableLiveData<State<UserResponse>>()

    fun login(userName: String, password: String) {
        execute(loadingConsumer = {
            user.postValue(State.Loading)
        }, successConsumer = {
            user.postValue(it)
        }, throwableConsumer = {
            user.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.login(SignInRequest(userName,password)))
    }
}