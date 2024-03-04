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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Annotates the ViewModel for dependency injection with Hilt.
@HiltViewModel
class MainViewModel @Inject constructor(
    // Injects the use case for getting food properties. Used to fetch food data based on search criteria.
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
    // Injects the use case to retrieve API response flow. Used for observing API response states.
    getApiResponseFlowUseCase: GetApiResponseFlowUseCase,
) : ViewModel() {

    // Holds the API response flow, initiated by invoking the getApiResponseFlowUseCase.
    val apiResponseFlow: StateFlow<FoodWrapper> = getApiResponseFlowUseCase.invoke()

    // A mutable state holding the current search ingredient entered by the user. Initially empty.
    var searchIngredient: String by mutableStateOf("")

    // A mutable state list holding the list of food properties entities. Initially empty.
    private var products = mutableStateListOf<FoodPropertiesEntity>()

    init {
        // Collects API response flow updates within the ViewModel scope.
        viewModelScope.launch {
            apiResponseFlow.collect { foodWrapper ->
                when (foodWrapper) {
                    // On successful API response, clears the current products list and adds new ones.
                    is FoodWrapper.Success -> {
                        products.clear()
                        products.addAll(foodWrapper.foodProperties)
                    }

                    // On receiving a 'NoResults' response, clears the products list.
                    is FoodWrapper.NoResults -> {
                        products.clear()
                    }

                    // Handles the 'Error' response. Currently no implementation.
                    is FoodWrapper.Error -> {
                    }

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

    // Function to initiate the API call through the use case with the current search ingredient.
    fun launchAPI() {
        viewModelScope.launch {
            getFoodPropertiesUseCase.invoke(searchIngredient)
        }
    }

    // Updates the current search ingredient with the new text value.
    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }
}