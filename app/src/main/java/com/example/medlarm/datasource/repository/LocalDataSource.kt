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

class LocalDataSource @Inject constructor
    (private val serviceApi: ServiceApi) : DataSource  {
    override fun signUp(signUpRequest: SignUpRequest): Single<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun login(signInRequest: SignInRequest): Single<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<AddMedicineResponse> {
        TODO("Not yet implemented")
    }

    override fun getMedicineList(categoryId: Int): Single<MedicinesList> {
        TODO("Not yet implemented")
    }
}