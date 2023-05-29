package com.example.homefinancialassistant.data.db.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToString(calendar: Calendar): String {
        val sdf = SimpleDateFormat(FORMAT_DATE, Locale.ROOT)
        val date = calendar.time
        return sdf.format(date)
    }

    @TypeConverter
    fun stringToCalendar(string: String): Calendar {
        val sdf = SimpleDateFormat(FORMAT_DATE, Locale.ROOT)
        val date: Date? = sdf.parse(string)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    companion object {
        const val FORMAT_DATE = "dd MM yyyy hh : mm"
    }
}