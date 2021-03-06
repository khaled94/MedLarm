package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmListItem
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.ServiceApi
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
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

    override fun aboutUs(): Single<AboutUsResponse> {
        return serviceApi.aboutUs()
    }

    override fun contactUs(): Single<ContactUsResponse> {
        return serviceApi.contactUs()
    }

    override fun updateProfile(editProfileRequest: EditProfileRequest): Single<UserResponse> {
        return serviceApi.updateProfile(editProfileRequest)
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse> {
        return serviceApi.changePassword(changePasswordRequest)
    }

    override fun getAlarmList(userId: Int): Single<AlarmListResponse> {
        return serviceApi.getAlarmList(userId)
    }

    override fun getUserHistory(userId: Int): Single<UserHistoryResponse> {
        return serviceApi.getUserHistory(userId)
    }

    override fun getAlarmByDate(userId: Int,date:String): Single<List<AlarmByDateResponseItem>> {
        return serviceApi.getAlarmByDate(userId,date)
    }

    override fun updateTakenAlarm(takenAlarmList: ArrayList<TakenAlarmListItem>): Single<UpdateTakenAlarmResponse> {
        return serviceApi.updateTakenAlarm(takenAlarmList)
    }

    override fun removeAlarm(alarmId: Int): Single<RemoveAlarmResponse> {
        return serviceApi.removeAlarm(alarmId)
    }

    override fun updateAlarm(updateAlarmRequest: UpdateAlarmRequest): Single<UpdateAlarmResponse> {
        return serviceApi.updateAlarm(updateAlarmRequest)
    }

    override fun getAlarmDetails(alarmId: Int): Single<AlarmDetails> {
        return serviceApi.getAlarmDetails(alarmId)
    }

    override fun getUserProfile(userId: Int): Single<UserProfileResponse> {
        return serviceApi.getUserProfile(userId)
    }

    override fun saveAlarmsToDatabase(alarmList: ArrayList<AlarmListResponseItem>) : Completable {
        TODO("Not yet implemented")
    }

    override fun getAlarmsFromDatabase(): Single<List<AlarmListResponseItem>> {
        TODO("Not yet implemented")
    }


}