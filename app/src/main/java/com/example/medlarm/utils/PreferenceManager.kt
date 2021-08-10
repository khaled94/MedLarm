package com.example.medlarm.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE)

    fun getCurrentLocale(): String {
        return preferences.getString("current_locale", LocaleManager.OPTION_PHONE_LANGUAGE)!!
    }

    fun setCurrentLocale(localeCode: String) {
        preferences.edit().putString("current_locale", localeCode).apply()
    }

    fun setData(userId: Int?) {
        with(preferences.edit()) {
            putInt("user_id", userId!!)
            apply()
        }
    }
}