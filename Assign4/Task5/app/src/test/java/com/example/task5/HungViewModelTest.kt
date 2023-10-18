package com.example.task5

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class HungViewModelTest {
    @Test
    fun gameOver() {
        var hungViewModel = HungViewModel()
        val q = hungViewModel.getCurrQuestion()
        for(i in 0..q.length()-1){
            var curr = q.fullArray[i]
            q.insert(curr)
        }
        assertEquals(true, hungViewModel.gameOver())
    }

    @Test
    fun testShowHalf() {
        var hungViewModel = HungViewModel()
        hungViewModel.showHalf()
        val q = hungViewModel.getCurrQuestion()
        for(i in 0..q.length()-1){
            var curr = q.getChar(i)[0]
            if(curr != ' ') assertEquals(q.fill[i], true)
        }
    }
}