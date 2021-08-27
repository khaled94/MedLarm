package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Path
import java.util.*

interface DataSource {

    fun signUp(signUpRequest: SignUpRequest) : Single<UserResponse>
    fun login(signInRequest: SignInRequest): Single<UserResponse>
    fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<AddMedicineResponse>
    fun getMedicineList(categoryId : Int): Single<MedicinesList>
    fun aboutUs(): Single<AboutUsResponse>
    fun contactUs(): Single<ContactUsResponse>
    fun updateProfile(signUpRequest: SignUpRequest): Single<UserResponse>
    fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse>
    fun getAlarmList(userId: Int): Single<AlarmListResponse>
    fun getUserHistory(userId: Int): Single<UserHistoryResponse>
    fun getAlarmByDate(userId: Int,date: Date): Single<AlarmByDateResponse>
    fun updateTakenAlarm(takenAlarmList: TakenAlarmList): Single<UpdateTakenAlarmResponse>
    fun removeAlarm(alarmId: Int): Single<RemoveAlarmResponse>
    fun updateAlarm(updateAlarmRequest: UpdateAlarmRequest): Single<UpdateAlarmResponse>
    fun getAlarmDetails(alarmId: Int): Single<AlarmDetails>
    fun getUserProfile(userId: Int): Single<UserProfileResponse>

}