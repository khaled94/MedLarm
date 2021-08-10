package com.example.medlarm.datasource

import com.example.medlarm.data.model.requestModels.*
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmList
import com.example.medlarm.data.model.responseModels.*
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponse
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponse
import com.example.medlarm.data.model.responseModels.lookup.LookupsResponse
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.medicinestypelist.MedicineTypeList
import com.example.medlarm.data.model.responseModels.userhistory.UserHistoryResponse
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceApi {

    @GET("/GetAboutUs")
    fun aboutUs(): Single<AboutUsResponse>

    @GET("/GetContactUs")
    fun contactUs(): Single<ContactUsResponse>

    @POST("/PostSignUp")
    fun signUp(@Body body: SignUpRequest): Single<UserResponse>

    @POST("/PostSignUp")
    fun updateProfile(@Body body: SignUpRequest): Single<UserResponse>

    @POST("/PostSignIn")
    fun login(@Body body: SignInRequest): Single<UserResponse>

    @POST("/ChangePassword")
    fun changePassword(): Single<ChangePasswordResponse>

    @GET("/GetLookups")
    fun getLookups(): Single<LookupsResponse>

    @GET("/GetLookupsValueByCategotyId/1")
    fun getMedicineTypeList(): Single<MedicineTypeList>

    @GET("GetMedicinesByCategotyId/{categoryId}")
    fun getMedicineListByCategory(@Path("categoryId") categoryId: Int): Single<MedicinesList>

    @POST("PostFreqIntakeAndPatientAlarm")
    fun addMedicine(@Body body : AddMedicineRequest): Single<AddMedicineResponse>

    @POST("GetFreqIntakeAndPatientAlarm/{UserId}")
    fun getAlarmList(): Single<AlarmListResponse>

    @GET("GetUserHistory/{UserId}")
    fun getUserHistory(): Single<UserHistoryResponse>

    @GET("PatientAlarmsByDate/{UserId}/{Date}")
    fun getAlarmByDate(): Single<AlarmByDateResponse>

    @POST("UpdateTakenAlarms")
    fun updateTakenAlarm(@Body body : TakenAlarmList): Single<UpdateTakenAlarmResponse>

    @POST("RemoveAlarm/{AlarmId}")
    fun removeAlarm(): Single<RemoveAlarmResponse>

    @POST("UpdateFrequentIntake")
    fun updateAlarm(@Body body : UpdateAlarmRequest): Single<UpdateAlarmResponse>

    @POST("GetFreqIntake/{AlarmId}")
    fun getAlarmDetails(): Single<AlarmDetails>

    @POST("GetUser/{UserId}")
    fun getUserProfile(): Single<UserProfileResponse>
}