package com.persival.k_lory.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.GetApiResponseFlowUseCase
import com.persival.k_lory.domain.food_facts.GetFoodPropertiesUseCase
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
    getApiResponseFlowUseCase: GetApiResponseFlowUseCase,
) : ViewModel() {

    private val _apiResponseFlow = getApiResponseFlowUseCase.invoke()
    val apiResponseFlow: StateFlow<FoodWrapper> = _apiResponseFlow

    var searchIngredient: String by mutableStateOf("")
        private set

    private val _displayableProducts = MutableStateFlow<List<MainViewState>>(emptyList())
    val displayableProducts: StateFlow<List<MainViewState>> = _displayableProducts

    init {
        observeApiResponseFlow()
    }

    private fun observeApiResponseFlow() {
        viewModelScope.launch {
            apiResponseFlow.collectLatest { foodWrapper ->
                when (foodWrapper) {
                    is FoodWrapper.Success -> _displayableProducts.value =
                        foodWrapper.foodProperties.map { it.toMainViewState() }

                    is FoodWrapper.NoResults, is FoodWrapper.Error -> _displayableProducts.value = emptyList()

                    // Handles the 'Init' state. Currently no implementation.
                    is FoodWrapper.Init -> {
                    }

                    // Handles the 'Loading' state. Currently no implementation.
                    FoodWrapper.Loading -> {
                    }
                }
            }
        }
    }

    fun launchAPI() {
        if (searchIngredient.isNotBlank()) {
            viewModelScope.launch {
                getFoodPropertiesUseCase.invoke(searchIngredient)
            }
        }
    }

    fun updateTextFieldValue(text: String) {
        searchIngredient = text.trim()
    }

    private fun FoodPropertiesEntity.toMainViewState() = MainViewState(
        description = description?.capitalizeFully() ?: "",
        ingredients = ingredients?.capitalizeFully() ?: "",
        servingSizeUnit = servingSizeUnit ?: "",
        servingSize = servingSize?.toString() ?: "",
        energy = energy?.toString() ?: "N/A",
        protein = protein?.toString() ?: "N/A",
        carbohydrate = carbohydrate?.toString() ?: "N/A",
        fat = fat?.toString() ?: "N/A",
    )

    private fun String.capitalizeFully(): String = split(" ").joinToString(" ") {
        it.lowercase(Locale.getDefault()).replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
    }
}
