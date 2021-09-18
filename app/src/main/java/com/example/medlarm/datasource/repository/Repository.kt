package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.utils.State
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*

interface Repository {
    fun signUp(signUpRequest: SignUpRequest): Single<State<UserResponse>>
    fun login(signInRequest: SignInRequest): Single<State<UserResponse>>
    fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<State<AddMedicineResponse>>
    fun getMedicineList(categoryId: Int): Single<State<MedicinesList>>
    fun aboutUs(): Single<State<AboutUsResponse>>
    fun contactUs(): Single<State<ContactUsResponse>>
    fun updateProfile(signUpRequest: SignUpRequest): Single<State<UserResponse>>
    fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<State<ChangePasswordResponse>>
    fun getAlarmList(userId: Int): Single<State<AlarmListResponse>>
    fun getUserHistory(userId: Int): Single<State<UserHistoryResponse>>
    fun getAlarmByDate(userId: Int,date: String): Single<State<List<AlarmByDateResponseItem>>>
    fun updateTakenAlarm(takenAlarmList: TakenAlarmList): Single<State<UpdateTakenAlarmResponse>>
    fun removeAlarm(alarmId: Int): Single<State<RemoveAlarmResponse>>
    fun updateAlarm(updateAlarmRequest: UpdateAlarmRequest): Single<State<UpdateAlarmResponse>>
    fun getAlarmDetails(alarmId: Int): Single<State<AlarmDetails>>
    fun getUserProfile(userId: Int): Single<State<UserProfileResponse>>

    fun saveAlarmsToDatabase(alarmList: ArrayList<AlarmListResponseItem>) : Completable
    fun getAlarmsFromDatabase(): Single<List<AlarmListResponseItem>>
}