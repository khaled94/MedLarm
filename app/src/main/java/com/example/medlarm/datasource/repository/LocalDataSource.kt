package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.ServiceApi
import com.example.medlarm.datasource.room.DAOAccess
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor
    (private val daoAccess: DAOAccess) : DataSource  {
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

    override fun aboutUs(): Single<AboutUsResponse> {
        TODO("Not yet implemented")
    }

    override fun contactUs(): Single<ContactUsResponse> {
        TODO("Not yet implemented")
    }

    override fun updateProfile(signUpRequest: SignUpRequest): Single<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse> {
        TODO("Not yet implemented")
    }

    override fun getAlarmList(userId: Int): Single<AlarmListResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserHistory(userId: Int): Single<UserHistoryResponse> {
        TODO("Not yet implemented")
    }

    override fun getAlarmByDate(userId: Int,date: Date): Single<AlarmByDateResponse> {
        TODO("Not yet implemented")
    }

    override fun updateTakenAlarm(takenAlarmList: TakenAlarmList): Single<UpdateTakenAlarmResponse> {
        TODO("Not yet implemented")
    }

    override fun removeAlarm(alarmId: Int): Single<RemoveAlarmResponse> {
        TODO("Not yet implemented")
    }

    override fun updateAlarm(updateAlarmRequest: UpdateAlarmRequest): Single<UpdateAlarmResponse> {
        TODO("Not yet implemented")
    }

    override fun getAlarmDetails(alarmId: Int): Single<AlarmDetails> {
        TODO("Not yet implemented")
    }

    override fun getUserProfile(userId: Int): Single<UserProfileResponse> {
        TODO("Not yet implemented")
    }
}