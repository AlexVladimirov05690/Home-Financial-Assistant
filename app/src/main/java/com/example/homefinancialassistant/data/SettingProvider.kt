package com.example.homefinancialassistant.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class SettingProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)


    init {
        if (preferences.getBoolean(KEY_FIRST_LAUNCH, false)) {
            preferences.edit {
                putString(
                    KEY_DEFAULT_SCREEN,
                    DEFAULT_SCREEN
                )
            }
            preferences.edit { putBoolean(KEY_FIRST_LAUNCH, false) }
        }
    }

    fun saveDefaultScreen(screen: String) {
        preferences.edit { putString(KEY_DEFAULT_SCREEN, screen) }
    }

    fun getDefaultScreen(): String {
        return preferences.getString(
            KEY_DEFAULT_SCREEN,
            DEFAULT_SCREEN
        ) ?: DEFAULT_SCREEN
    }


    fun changeDate(calendar: Calendar) {
        val date = calendar.get(Calendar.DATE).toString() + calendar.get(Calendar.MONTH)
            .toString() + calendar.get(Calendar.YEAR).toString()
        preferences.edit().putString(DATE_OF_LAST_REQUEST, date).apply()
    }

    private fun getDateOfLastRequest(): String {
        return preferences.getString(DATE_OF_LAST_REQUEST, "000000").toString()
    }


    fun dateCompare(calendar: Calendar): Boolean {
        val dateToday = calendar.get(Calendar.DATE).toString() + calendar.get(Calendar.MONTH)
            .toString() + calendar.get(Calendar.YEAR).toString()
        return (dateToday == getDateOfLastRequest())
    }


    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val DEFAULT_SCREEN = "home"
        private const val KEY_DEFAULT_SCREEN = "default screen"
        private const val DATE_OF_LAST_REQUEST = "date_of_last_request"
        private const val SETTINGS = "settings"
    }
}