package com.example.medlarm.utils

interface IPreferenceHelper {
    fun setLanguage(language: String)
    fun getLanguage(): String
    fun clearPrefs()
}