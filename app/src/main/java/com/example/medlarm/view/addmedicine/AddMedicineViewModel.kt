package com.example.medlarm.view.addmedicine

import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.datasource.repository.Repository
import com.example.medlarm.utils.BaseSchedulerProvider
import com.example.medlarm.utils.BaseViewModel
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import javax.inject.Inject

class AddMedicineViewModel  @Inject constructor(
    private val repository: Repository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {

    private val errorHandler: ErrorHandler = ErrorHandler()
    val medicineList = MutableLiveData<State<MedicinesList>>()

   /* fun addMedicine(categoryId: String) {
        execute(loadingConsumer = {
            medicinesList.postValue(State.Loading)
        }, successConsumer = {
            user.postValue(it)
        }, throwableConsumer = {
            user.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.login(SignInRequest(userName,password)))
    }*/

     fun getMedicineList(categoryId: Int) {
        execute(loadingConsumer = {
            medicineList.postValue(State.Loading)
        }, successConsumer = {
            medicineList.postValue(it)
        }, throwableConsumer = {
            medicineList.postValue(State.Error(exception = errorHandler.getError(it)))
        }, useCase = repository.getMedicineList(categoryId))
    }



}