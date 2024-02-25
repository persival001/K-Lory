package com.persival.k_lory.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    // TextField state
    var searchIngredient: String by mutableStateOf("")

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {

    }
}