package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.AddMedicineRequest
import com.example.medlarm.data.model.requestModels.SignInRequest
import com.example.medlarm.data.model.requestModels.SignUpRequest
import com.example.medlarm.data.model.responseModels.medicineslist.MedicinesList
import com.example.medlarm.data.model.responseModels.userresponse.UserResponse
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import io.reactivex.Single
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

    override fun addMedicine(addMedicineRequest: AddMedicineRequest) {
        TODO("Not yet implemented")
    }

    override fun getMedicineList(categoryId: Int) : Single<State<MedicinesList>>{
        return remoteDataSource.getMedicineList(categoryId).consumeSingle()
    }
}
