package com.example.medlarm.utils

import retrofit2.HttpException
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class ErrorHandler  {
    fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.NetworkError("No internet connection")
            is HttpException -> {
                when (throwable.code()) {
                    HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> ErrorEntity.NetworkError("No internet connection")
                    HttpsURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    HttpsURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    HttpsURLConnection.HTTP_BAD_REQUEST -> ErrorEntity.BadRequest
                    HttpsURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    else -> ErrorEntity.UnKnownError
                }
            }
            else -> ErrorEntity.UnKnownError
        }
    }
}