package com.example.medlarm.datasource.repository

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
      private val mSharedPreferences: SharedPreferences?) {

        init {
            instance = this
        }

        companion object {
            @get:Synchronized
            lateinit var instance: AppPreferencesHelper
        }

    val prefLanguage = "pref.language"
    var language: String?
        get() = mSharedPreferences?.getString(prefLanguage, "en")
        set(value) = mSharedPreferences?.edit()?.putString(prefLanguage, value)?.apply()!!
}