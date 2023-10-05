package com.example.cs501_hw3_q6

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test

class QuestionViewModelTest {

    // JVM test 1 for Task 6
    @Test
    fun generateQuestion() {
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        questionViewModel.generateSubtraction()
        assertEquals("-", questionViewModel.operation)
        assertEquals(questionViewModel.answer, questionViewModel.topNum - questionViewModel.bottomNum)
    }

    // JVM test 2 for Task 6
    @Test
    fun nextQuestion() {
        val savedStateHandle = SavedStateHandle()
        val questionViewModel = QuestionViewModel(savedStateHandle)
        questionViewModel.nextQuestion()
        assertEquals(1, questionViewModel.getCurrentVal)
    }
}