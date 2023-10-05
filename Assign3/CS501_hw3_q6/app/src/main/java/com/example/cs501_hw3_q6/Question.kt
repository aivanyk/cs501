package com.example.cs501_hw3_q6
import androidx.annotation.StringRes

/*
* The class that stores the information of a question
* */
data class Question(@StringRes val topNumber: Int, @StringRes val bottomNumber: Int, val answer: Int, val operation: String)
