package com.example.medlarm.view.editmedicine

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.UpdateAlarmRequest
import com.example.medlarm.data.model.responseModels.AlarmDetails
import com.example.medlarm.data.model.responseModels.UpdateAlarmResponse
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class EditMedicineViewModel@Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val alarm = MutableLiveData<State<UpdateAlarmResponse>>()
    val alarmDetails = MutableLiveData<State<AlarmDetails>>()

    fun editAlarm(
        Id: Int, MedicineId: Int, UserId: Int, FreqIntakeNo: Int, FreqIntakeType: Int,
        DurationNo: Int, DurationType: Int, StartDate: String, StartTime: String)
    {
        execute(
            loadingConsumer = {
                alarm.postValue(State.Loading)
            },
            successConsumer = {
                alarm.postValue(it)
            },
            throwableConsumer = {
                alarm.postValue(State.Error(exception = errorHandler.getError(it)))
            },
            useCase = repository.updateAlarm(
                UpdateAlarmRequest(
                    Id, MedicineId, UserId, FreqIntakeNo, FreqIntakeType,
                    DurationNo, DurationType, StartDate, StartTime
                )
            )
        )
    }

    fun getAlarmDetails(alarmId: Int){
        execute(
            loadingConsumer = {
                alarmDetails.postValue(State.Loading)
            },
            successConsumer = {
                alarmDetails.postValue(it)
            },
            throwableConsumer = {
                alarmDetails.postValue(State.Error(exception = errorHandler.getError(it)))
            },
            useCase = repository.getAlarmDetails(alarmId)
        )
    }
}