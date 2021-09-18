package com.example.medlarm.view.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val alarmByDateList = MutableLiveData<State<List<AlarmByDateResponseItem>>>()
    val remoteAlarmList = MutableLiveData<State<AlarmListResponse>>()
    val localAlarmList = MutableLiveData<List<AlarmListResponseItem>>()

    fun getAlarmByDate(userId: Int,date: String) {
        execute(loadingConsumer = {
            alarmByDateList.postValue(State.Loading)
        }, successConsumer = {
            alarmByDateList.postValue(it)
        }, throwableConsumer = {
            alarmByDateList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getAlarmByDate(userId,date))
    }

    fun getAlarms(userId: Int){
        execute(loadingConsumer = {
            remoteAlarmList.postValue(State.Loading)
        }, successConsumer = {
            remoteAlarmList.postValue(it)
        }, throwableConsumer = {
            remoteAlarmList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getAlarmList(userId))
    }

    @SuppressLint("CheckResult")
    fun addAlarmsToDatabase(){
        repository.getAlarmsFromDatabase().doOnSuccess {
            localAlarmList.value = it
        }.doOnError {

        }
    }
}