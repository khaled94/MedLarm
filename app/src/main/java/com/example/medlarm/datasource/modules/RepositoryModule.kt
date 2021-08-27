package com.example.medlarm.datasource.modules

import android.content.SharedPreferences
import com.example.medlarm.datasource.ServiceApi
import com.example.medlarm.datasource.repository.*
import com.example.medlarm.datasource.room.DAOAccess
import com.example.medlarm.di.DI
import com.example.medlarm.utils.ErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    @Named(DI.REMOTE_DATA_SOURCE)
    fun provideRemoteDataSource(serviceApi: ServiceApi): DataSource =
        RemoteDataSource(serviceApi = serviceApi)

    @Provides
    @Singleton
    @Named(DI.LOCAL_DATA_SOURCE)
    fun provideLocalDataSource(daoAccess: DAOAccess): DataSource =
    LocalDataSource(daoAccess = daoAccess)

    @Singleton
    @Provides
    fun provideAppPrefHelper(sharedPref: SharedPreferences): AppPreferencesHelper {
        return AppPreferencesHelper(mSharedPreferences = sharedPref)
    }

    @Singleton
    @Provides
    fun providesRemoteRepository(
        @Named(DI.REMOTE_DATA_SOURCE) remoteDataSource: DataSource,
        @Named(DI.LOCAL_DATA_SOURCE) localDataSource: DataSource,
        sharedPref: AppPreferencesHelper
    ): Repository =
        RepositoryImplementer(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            errorHandler = ErrorHandler()
        )
}