package com.example.medlarm.view.home

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val alarms = MutableLiveData<State<AlarmByDateResponse>>()

    fun getAlarmByDate(userId: Int,date: Date) {
        execute(loadingConsumer = {
            alarms.postValue(State.Loading)
        }, successConsumer = {
            alarms.postValue(it)
        }, throwableConsumer = {
            alarms.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getAlarmByDate(userId,date))
    }
}