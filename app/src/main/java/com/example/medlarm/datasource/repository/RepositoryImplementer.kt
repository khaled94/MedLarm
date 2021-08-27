package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class RepositoryImplementer @Inject constructor(
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
    private val errorHandler: ErrorHandler,
) : Repository{

    private fun <T : Any> Single<T>.consumeSingle(onSuccess: ((obj: T) -> Unit)? = null): Single<State<T>> {
        return this.map {
            onSuccess?.invoke(it)
            State.Success(it) as State<T>
        }.onErrorReturn {
            State.Error(exception = errorHandler.getError(it))
        }
    }

   override fun signUp(signUpRequest: SignUpRequest): Single<State<UserResponse>> {
        return remoteDataSource.signUp(signUpRequest).consumeSingle()
    }

    override fun login(signInRequest :SignInRequest): Single<State<UserResponse>> {
        return remoteDataSource.login(signInRequest).consumeSingle()
    }

    override fun addMedicine(addMedicineRequest: AddMedicineRequest): Single<State<AddMedicineResponse>> {
        return remoteDataSource.addMedicine(addMedicineRequest).consumeSingle()
    }

    override fun getMedicineList(categoryId: Int) : Single<State<MedicinesList>>{
        return remoteDataSource.getMedicineList(categoryId).consumeSingle()
    }

    override fun aboutUs(): Single<State<AboutUsResponse>> {
        return remoteDataSource.aboutUs().consumeSingle()
    }

    override fun contactUs(): Single<State<ContactUsResponse>> {
        return remoteDataSource.contactUs().consumeSingle()
    }

    override fun updateProfile(signUpRequest: SignUpRequest): Single<State<UserResponse>> {
        return remoteDataSource.updateProfile(signUpRequest).consumeSingle()
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest): Single<State<ChangePasswordResponse>> {
        return remoteDataSource.changePassword(changePasswordRequest).consumeSingle()
    }

    override fun getAlarmList(userId: Int): Single<State<AlarmListResponse>> {
        return remoteDataSource.getAlarmList(userId).consumeSingle()
    }

    override fun getUserHistory(userId: Int): Single<State<UserHistoryResponse>> {
        return remoteDataSource.getUserHistory(userId).consumeSingle()
    }

    override fun getAlarmByDate(userId: Int, date: Date): Single<State<AlarmByDateResponse>> {
        return remoteDataSource.getAlarmByDate(userId,date).consumeSingle()
    }

    override fun updateTakenAlarm(takenAlarmList: TakenAlarmList): Single<State<UpdateTakenAlarmResponse>> {
        return remoteDataSource.updateTakenAlarm(takenAlarmList).consumeSingle()
    }

    override fun removeAlarm(alarmId: Int): Single<State<RemoveAlarmResponse>> {
        return remoteDataSource.removeAlarm(alarmId).consumeSingle()
    }

    override fun updateAlarm(updateAlarmRequest: UpdateAlarmRequest): Single<State<UpdateAlarmResponse>> {
        return remoteDataSource.updateAlarm(updateAlarmRequest).consumeSingle()
    }

    override fun getAlarmDetails(alarmId: Int): Single<State<AlarmDetails>> {
        return remoteDataSource.getAlarmDetails(alarmId).consumeSingle()
    }

    override fun getUserProfile(userId: Int): Single<State<UserProfileResponse>> {
        return remoteDataSource.getUserProfile(userId).consumeSingle()
    }
}
