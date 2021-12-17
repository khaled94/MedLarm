package com.example.medlarm.datasource.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmListItem
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.datasource.room.AlarmEntity
import com.example.medlarm.datasource.room.AlarmDao
import com.example.medlarm.datasource.room.AlarmMapper
import io.reactivex.Completable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor
    (
    private val alarmDao: AlarmDao,
    private val alarmMapper: AlarmMapper= AlarmMapper()
) : DataSource {

    var userAlarmList = mutableListOf<AlarmListResponseItem>()

    private val _data by lazy { MutableLiveData<List<AlarmEntity>>() }
    val data: LiveData<List<AlarmEntity>>
        get() = _data

    private val _isInProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isInProgress

    private val _isError by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean>
        get() = _isError

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

    override fun updateProfile(editProfileRequest: EditProfileRequest): Single<UserResponse> {
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

    override fun getAlarmByDate(userId: Int, date: String): Single<List<AlarmByDateResponseItem>> {
        TODO("Not yet implemented")
    }

    override fun updateTakenAlarm(takenAlarmList: ArrayList<TakenAlarmListItem>): Single<UpdateTakenAlarmResponse> {
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

    override fun saveAlarmsToDatabase(alarmList: ArrayList<AlarmListResponseItem>) : Completable {
        alarmDao.nukeTable()
        val entityList : List<AlarmEntity> = alarmList.map {
            alarmMapper.mapFromModelToEntity(model = it)
        }
        return Completable.fromAction {
            alarmDao.insertData(entityList)
        }
    }

    override fun getAlarmsFromDatabase(): Single<List<AlarmListResponseItem>> {
        val alarmList = alarmDao.getAllAlarms().map {
            alarmMapper.mapFromEntityToModel(it)
        }
        sortDates(alarmList)
        return Single.just(userAlarmList)
    }
    //return Single.just(alarmDao.getAllAlarms().value?.toList())

    @SuppressLint("LogNotTimber")
    private fun sortDates(dates : List<AlarmListResponseItem>){
        val calender: Calendar = Calendar.getInstance()
        Log.e("first date" , specialFormat(dates[0].Date,dates[0].Time).toString())
        Log.e("current date",calender.time.toString())
        Log.e(" is first after ",calender.time.after(specialFormat(dates[0].Date,dates[0].Time)).toString())
        Log.e("dates",dates.toString())
        Log.e("size",dates.size.toString())

        userAlarmList = dates.filter {
            specialFormat(it.Date,it.Time).after(calender.time)
        }.
        sortedBy {
            specialFormat(it.Date,it.Time)
        }.toMutableList()
        Log.e("dates after",userAlarmList.toString())
        Log.e("size after",userAlarmList.size.toString())
    }

    private fun specialFormat(inputDate : String , inputTime : String): Date {
        val myDateFormatter = inputDate.substring(0,10)+" "+inputTime
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return sdf.parse(myDateFormatter)!!
    }

}