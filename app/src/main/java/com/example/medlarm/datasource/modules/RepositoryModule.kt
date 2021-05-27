package com.example.medlarm.datasource.modules

import android.content.SharedPreferences
import com.example.medlarm.datasource.ServiceApi
import com.example.medlarm.datasource.repository.*
import com.example.medlarm.utils.ErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(serviceApi: ServiceApi): DataSource =
        RemoteDataSource(serviceApi = serviceApi)

  /*  @Singleton
    @Provides
    fun provideAppPrefHelper(sharedPref: SharedPreferences): AppPreferencesHelper {
        return AppPreferencesHelper(mSharedPreferences = sharedPref)
    }*/

    @Singleton
    @Provides
    fun providesRemoteRepository(
        remoteDataSource: DataSource
    ): Repository =
        RepositoryImplementer(
            remoteDataSource = remoteDataSource,
            errorHandler = ErrorHandler()
        )
}