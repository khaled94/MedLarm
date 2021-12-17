package com.example.medlarm.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences("sp", Context.MODE_PRIVATE)
    //var userId : Int = 0

    fun getCurrentLocale(): String {
        return preferences.getString("current_locale", LocaleManager.OPTION_PHONE_LANGUAGE)!!
    }

    fun setCurrentLocale(localeCode: String) {
        preferences.edit().putString("current_locale", localeCode).apply()
    }


    fun setUserId(userId: Int?) {
        with(preferences.edit()) {
            putInt("user_id", userId!!)
            apply()
        }
    }

    fun getUserId(): Int {
        return preferences.getInt("user_id", 0)
    }

    fun setUserName(userName: String?) {
        with(preferences.edit()) {
            putString("user_name", userName!!)
            apply()
        }
    }

    fun getUserName(): String? {
        return preferences.getString("user_name", "")
    }
    fun setCurrentAlarmId(alarmId: Int?) {
        with(preferences.edit()) {
            putInt("alarm_id", alarmId!!)
            apply()
        }
    }

    fun getCurrentAlarmId(): Int {
        return preferences.getInt("alarm_id", 0)
    }

    fun setCurrentAlarmIndex(alarmId: Int?) {
        with(preferences.edit()) {
            putInt("alarm_id", alarmId!!)
            apply()
        }
    }

    fun getCurrentAlarmIndex(): Int {
        return preferences.getInt("alarm_id", 0)
    }

    fun setRingName(ringName: String?) {
        with(preferences.edit()) {
            putString("ring_name", ringName!!)
            apply()
        }
    }

    fun getRingName(): String? {
        return preferences.getString("ring_name", "alarm1")
    }



    fun clear () {
     preferences.edit().clear().apply()
    }

}