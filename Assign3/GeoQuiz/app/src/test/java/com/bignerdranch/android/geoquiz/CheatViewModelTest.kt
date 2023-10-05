package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test

class CheatViewModelTest {

    //JVM test for Task 3
    @Test
    fun initiallyProvidesCheater() {
        val savedStateHandle = SavedStateHandle()
        val cheatViewModel = CheatViewModel(savedStateHandle)
        assertEquals(false, cheatViewModel.isCheater)
    }

}