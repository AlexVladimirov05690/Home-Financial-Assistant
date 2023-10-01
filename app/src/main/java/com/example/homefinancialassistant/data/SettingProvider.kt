package com.example.homefinancialassistant.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import java.util.*

class SettingProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    init {
        if (preferences.getBoolean(KEY_FIRST_LAUNCH, false)) {
            preferences.edit { putBoolean(KEY_DARK_MODE, false) }
            preferences.edit { putBoolean(KEY_AUTO_DARK_MODE, false) }
            preferences.edit { putBoolean(KEY_CHANGE_DARK_MODE, false) }
            preferences.edit { putString(THEME, "auto") }
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

    fun getKeyStartScreenFromScreenSettings(): Boolean {
        return preferences.getBoolean(KEY_CHANGE_DARK_MODE, false)
    }

    fun changeKeyStartScreenFromScreenSettings(boolean: Boolean) {
        preferences.edit {
            putBoolean(KEY_CHANGE_DARK_MODE, boolean)
        }
    }

    fun getAutoDarkTheme(): Boolean {
        return preferences.getBoolean(KEY_AUTO_DARK_MODE, false)
    }


    fun changeDate(calendar: Calendar) {
        val date = calendar.get(Calendar.DATE).toString() + "." + (calendar.get(Calendar.MONTH) + 1)
            .toString() + "." + calendar.get(Calendar.YEAR).toString()
        preferences.edit().putString(DATE_OF_LAST_REQUEST, date).apply()
    }


    fun changeThemeApp() {
        return when (preferences.getString(THEME, "auto")) {
            "auto" -> {
                preferences.edit { putString(THEME, "light") }
                preferences.edit { putBoolean(KEY_AUTO_DARK_MODE, false) }
                preferences.edit { putBoolean(KEY_DARK_MODE, false) }
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            "light" -> {
                preferences.edit { putString(THEME, "dark") }
                preferences.edit { putBoolean(KEY_AUTO_DARK_MODE, false) }
                preferences.edit { putBoolean(KEY_DARK_MODE, true) }
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            "dark" -> {
                preferences.edit { putString(THEME, "auto") }
                preferences.edit { putBoolean(KEY_AUTO_DARK_MODE, true) }
                preferences.edit { putBoolean(KEY_DARK_MODE, false) }
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            }

            else -> {
                preferences.edit { putString(THEME, "auto") }
            }
        }
    }

    fun getThemeApp(): String? {
        return preferences.getString(THEME, "auto")
    }

    fun getDateOfLastRequest(): String {
        return preferences.getString(DATE_OF_LAST_REQUEST, "000000").toString()
    }


    fun dateCompare(calendar: Calendar): Boolean {
        val dateToday = calendar.get(Calendar.DATE).toString() + calendar.get(Calendar.MONTH)
            .toString() + calendar.get(Calendar.YEAR).toString()
        return (dateToday == getDateOfLastRequest())
    }


    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_CHANGE_DARK_MODE = "Изменение тёмного режима"
        private const val KEY_DARK_MODE = "dark theme"
        private const val KEY_AUTO_DARK_MODE = "auto dark theme"
        private const val DEFAULT_SCREEN = "Главный экран"
        private const val KEY_DEFAULT_SCREEN = "default screen"
        private const val DATE_OF_LAST_REQUEST = "date_of_last_request"
        private const val SETTINGS = "settings"
        private const val THEME = "Тема"

    }
}