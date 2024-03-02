package com.persival.k_lory.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.GetApiResponseFlowUseCase
import com.persival.k_lory.domain.food_facts.GetFoodPropertiesUseCase
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import com.persival.k_lory.ui.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
    private val resourceProvider: ResourceProvider,
    getApiResponseFlowUseCase: GetApiResponseFlowUseCase,
) : ViewModel() {

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    val apiResponseFlow: StateFlow<FoodWrapper> = getApiResponseFlowUseCase.invoke()

    var searchIngredient: String by mutableStateOf("")

    var products = mutableStateListOf<FoodPropertiesEntity>()
        private set

    init {
        viewModelScope.launch {
            apiResponseFlow.collect { foodWrapper ->
                when (foodWrapper) {
                    is FoodWrapper.Success -> {
                        products.clear()
                        products.addAll(foodWrapper.foodProperties)
                    }

                    is FoodWrapper.NoResults -> {
                        products.clear()
                    }

                    is FoodWrapper.Error -> {
                    }

                    FoodWrapper.Loading -> {
                    }
                }
            }
        }
    }

    fun launchAPI() {
        // Trigger the use case to start loading and eventually update the apiResponseFlow
        viewModelScope.launch {
            getFoodPropertiesUseCase.invoke(searchIngredient)
        }
    }

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun toastShown() {
        _toastMessage.value = null
    }
}
