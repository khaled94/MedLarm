package com.example.medlarm.view.addmedicine

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.responseModels.AddMedicineResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class AddMedicineViewModel @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val medicineList = MutableLiveData<State<MedicinesList>>()
    val addMedicineResult = MutableLiveData<State<AddMedicineResponse>>()
    val alarmList = MutableLiveData<State<AlarmListResponse>>()

    fun addMedicine(
        medicineId: Int,userId: Int, freqIntakeNo: Int, freqIntakeType: Int, durationNo: Int,
        durationType: Int, startDate: String, startTime: String
    ) {
        execute(loadingConsumer = {
            addMedicineResult.postValue(State.Loading)
        }, successConsumer = {
            addMedicineResult.postValue(it)
        }, throwableConsumer = {
            addMedicineResult.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.addMedicine(
            AddMedicineRequest(
                medicineId, userId, freqIntakeNo,
                freqIntakeType, durationNo, durationType, startDate, startTime
            )
        )
        )
    }

    fun getMedicineList(categoryId: Int) {
        execute(loadingConsumer = {
            medicineList.postValue(State.Loading)
        }, successConsumer = {
            medicineList.postValue(it)
        }, throwableConsumer = {
            medicineList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getMedicineList(categoryId))
    }

    fun getAlarms(userId: Int){
        execute(loadingConsumer = {
            alarmList.postValue(State.Loading)
        }, successConsumer = {
            alarmList.postValue(it)
        }, throwableConsumer = {
            alarmList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getAlarmList(userId))
    }


}