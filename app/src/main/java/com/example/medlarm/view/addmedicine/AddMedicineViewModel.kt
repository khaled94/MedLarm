package com.example.medlarm.view.addmedicine

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.responseModels.AddMedicineResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AddMedicineViewModel @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val medicineList = MutableLiveData<State<MedicinesList>>()
    val addMedicineResult = MutableLiveData<State<AddMedicineResponse>>()
    val remoteAlarmList = MutableLiveData<State<AlarmListResponse>>()
    var isSaved = MutableLiveData(false)
    val localAlarmList = MutableLiveData<List<AlarmListResponseItem>>()

    fun addNewMedicine(
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

    fun getRemoteAlarms(userId: Int){
        execute(loadingConsumer = {
            remoteAlarmList.postValue(State.Loading)
        }, successConsumer = {
            remoteAlarmList.postValue(it)
        }, throwableConsumer = {
            remoteAlarmList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getAlarmList(userId))
    }

    @SuppressLint("CheckResult")
    fun saveAlarmsToDatabase(remoteAlarms: AlarmListResponse){
        repository.saveAlarmsToDatabase(remoteAlarms)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isSaved.postValue(true)
                    Timber.v("Data saved success case")
                }, {
                    Timber.v("error case")
                })
    }

    @SuppressLint("CheckResult")
    fun getAlarmsFromDatabase(){
        repository.getAlarmsFromDatabase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    localAlarmList.postValue(it)
                    Timber.v("Data saved success case")
                }, {
                    Timber.v("error case")
                })
    }
      /* repository.getAlarmsFromDatabase().doOnSuccess {
           localAlarmList.value = it
       }.doOnError {

       }*/



}