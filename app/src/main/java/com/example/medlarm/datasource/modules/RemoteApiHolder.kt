package com.example.medlarm.datasource.modules

import com.example.medlarm.datasource.ServiceApi

class RemoteApiHolder {
    private var remoteApi: ServiceApi? = null
    fun getRemoteApi() = remoteApi
    fun setRemoteApi(value: ServiceApi) {
        this.remoteApi = value
    }
}