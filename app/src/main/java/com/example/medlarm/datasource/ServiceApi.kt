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
import java.util.*

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
    fun changePassword(@Body body: ChangePasswordRequest): Single<ChangePasswordResponse>

    @GET("/GetLookups")
    fun getLookups(): Single<LookupsResponse>

    @GET("/GetLookupsValueByCategotyId/1")
    fun getMedicineTypeList(): Single<MedicineTypeList>

    @GET("GetMedicinesByCategotyId/{categoryId}")
    fun getMedicineListByCategory(@Path("categoryId") categoryId: Int): Single<MedicinesList>

    @POST("PostFreqIntakeAndPatientAlarm")
    fun addMedicine(@Body body: AddMedicineRequest): Single<AddMedicineResponse>

    @POST("GetFreqIntakeAndPatientAlarm/{userId}")
    fun getAlarmList(@Path("userId") userId: Int): Single<AlarmListResponse>

    @GET("GetUserHistory/{userId}")
    fun getUserHistory(@Path("userId") userId: Int): Single<UserHistoryResponse>

    @GET("PatientAlarmsByDate/{userId}/{date}")
    fun getAlarmByDate(
        @Path("userId") userId: Int,
        @Path("date") date: Date
    ): Single<AlarmByDateResponse>

    @POST("UpdateTakenAlarms")
    fun updateTakenAlarm(@Body body: TakenAlarmList): Single<UpdateTakenAlarmResponse>

    @POST("RemoveAlarm/{AlarmId}")
    fun removeAlarm(@Path("alarmId") alarmId: Int): Single<RemoveAlarmResponse>

    @POST("UpdateFrequentIntake")
    fun updateAlarm(@Body body: UpdateAlarmRequest): Single<UpdateAlarmResponse>

    @POST("GetFreqIntake/{AlarmId}")
    fun getAlarmDetails(@Path("alarmId") alarmId: Int): Single<AlarmDetails>

    @POST("GetUser/{UserId}")
    fun getUserProfile(@Path("userId") userId: Int): Single<UserProfileResponse>
}