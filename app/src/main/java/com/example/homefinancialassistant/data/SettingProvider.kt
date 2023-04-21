package com.example.homefinancialassistant.data

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar

class SettingProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)


    init {
        if (preferences.getBoolean(KEY_FIRST_LAUNCH, false)) {
            preferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
        }
    }


    fun changeDate(calendar: Calendar) {
        val date = calendar.get(Calendar.DATE).toString() + calendar.get(Calendar.MONTH).toString() + calendar.get(Calendar.YEAR).toString()
        preferences.edit().putString(DATE_OF_LAST_REQUEST, date).apply()
    }

    private fun getDateOfLastRequest(): String {
        return preferences.getString(DATE_OF_LAST_REQUEST, "000000").toString()
    }



    fun dateCompare(calendar: Calendar) : Boolean {
        val dateToday = calendar.get(Calendar.DATE).toString() + calendar.get(Calendar.MONTH).toString() + calendar.get(Calendar.YEAR).toString()
        return (dateToday == getDateOfLastRequest())
    }




    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val DATE_OF_LAST_REQUEST = "date_of_last_request"
        private const val SETTINGS = "settings"
    }
}