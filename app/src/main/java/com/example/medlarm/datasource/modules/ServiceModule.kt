package com.example.medlarm.datasource.modules

import com.example.medlarm.datasource.ServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit, remoteApiHolder: RemoteApiHolder): ServiceApi {
        val serviceApi = retrofit.create(ServiceApi::class.java)
        remoteApiHolder.setRemoteApi(serviceApi)
        return serviceApi
    }
}