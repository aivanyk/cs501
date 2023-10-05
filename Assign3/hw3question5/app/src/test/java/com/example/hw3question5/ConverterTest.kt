package com.example.hw3question5

import org.junit.Assert.*
import org.junit.Test

class ConverterTest{
    private val testConverter:Converter = Converter()
    @Test
    fun testToCelsius() {
        assertEquals(0,testConverter.toCelsius(32))
    }

    @Test
    fun testToFahrenheit() {
        assertEquals(32,testConverter.toFahrenheit(0))
    }
}