package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.AddMedicineResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import io.reactivex.Single

interface DataSource {
    fun signUp(signUpRequest: SignUpRequest) : Single<UserResponse>
    fun login(signInRequest: SignInRequest): Single<UserResponse>
    fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<AddMedicineResponse>
    fun getMedicineList(categoryId : Int): Single<MedicinesList>
}