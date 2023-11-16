package com.bignerdranch.android.criminalintent.database

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CrimeTypeConverters {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }

//    @TypeConverter
//    fun fromDate(date: Date): String {
//        val locale = Locale.getDefault() // Get the device's current locale
//        val pattern = android.text.format.DateFormat.getBestDateTimePattern(locale, "MM/dd/yyyy") // Adjust the pattern based on the locale's preferred date format
//
//        val dateFormatter = SimpleDateFormat(pattern, locale)
//        return dateFormatter.format(date)
//    }
//
//    @TypeConverter
//    fun toDate(dateString: String): Date {
//        val formatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, defaultLocale)
//        return formatter.parse(dateString) ?: Date()
//    }
}
