package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.AddMedicineResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.ServiceApi
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor
    (private val serviceApi: ServiceApi) : DataSource {
    override fun signUp(signUpRequest: SignUpRequest): Single<UserResponse> {
        return serviceApi.signUp(signUpRequest)
    }

    override fun login(signInRequest: SignInRequest): Single<UserResponse> {
        return serviceApi.login(signInRequest)
    }

    override fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<AddMedicineResponse> {
        return serviceApi.addMedicine(addMedicineRequest)
    }

    override fun getMedicineList(categoryId: Int): Single<MedicinesList> {
        return serviceApi.getMedicineListByCategory(categoryId)
    }


}