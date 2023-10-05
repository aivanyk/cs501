package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
private const val TAG = "CheatViewModel"
class CheatViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)
}