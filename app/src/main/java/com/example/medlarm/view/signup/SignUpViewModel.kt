package com.example.medlarm.view.signup

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.responseModels.UserResponse
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

    fun login(dateOfBirth: String, password: String) {
     /*   execute(loadingConsumer = {
            user.postValue(State.Loading)
        }, successConsumer = {
            user.postValue(it)
        }, throwableConsumer = {
            user.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.login(userName = userName, password = password))*/
    }

}