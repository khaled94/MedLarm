package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.utils.State
import io.reactivex.Single

interface Repository {
   fun signUp(signUpRequest: SignUpRequest) : Single<State<UserResponse>>
   fun login(signInRequest: SignInRequest): Single<State<UserResponse>>
   fun addMedicine(addMedicineRequest: AddMedicineRequest)
   fun getMedicineList(categoryId : Int) : Single<State<MedicinesList>>
}