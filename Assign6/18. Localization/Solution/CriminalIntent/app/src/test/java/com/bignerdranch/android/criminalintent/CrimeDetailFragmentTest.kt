package com.bignerdranch.android.criminalintent

import android.text.format.DateFormat
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CrimeDetailFragmentTest{
    @Test
    fun format_date(){
        val date = Date(1638211200000)
        var expectedDateString = "November 29, 2021"
        var dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.US)
        var str = dateFormat.format(date)
        assertEquals(expectedDateString, str)

        expectedDateString = "2021年11月29日"
        dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.CHINA)
        str = dateFormat.format(date)
        assertEquals(expectedDateString, str)

        expectedDateString = "29. November 2021"
        dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.GERMAN)
        str = dateFormat.format(date)
        assertEquals(expectedDateString, str)
    }
}