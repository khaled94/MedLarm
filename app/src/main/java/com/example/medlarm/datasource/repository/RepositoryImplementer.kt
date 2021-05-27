package com.example.medlarm.datasource.repository

import com.example.medlarm.data.model.requestModels.SignInRequestBody
import com.example.medlarm.data.model.requestModels.SignUpRequestBody
import com.example.medlarm.data.model.responseModels.UserResponse
import com.example.medlarm.utils.ErrorHandler
import com.example.medlarm.utils.State
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImplementer @Inject constructor(
    private val remoteDataSource: DataSource,
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

  /*  override fun signUp(signUpRequestBody: SignUpRequestBody): Single<State<UserResponse>> {
        return remoteDataSource.signUp(signUpRequestBody).consumeSingle()
    }*/

    /*override fun login(userName: String , password : String): Single<State<UserResponse>> {
       // var signInRequestBody = SignUpRequestBody()
        return remoteDataSource.login(signInRequestBody).consumeSingle()
    }*/
}
