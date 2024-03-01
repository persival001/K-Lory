package com.persival.k_lory.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.GetFoodPropertiesUseCase
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
) : ViewModel() {

    var searchIngredient: String by mutableStateOf("")
    var products = mutableStateListOf<FoodPropertiesEntity>()
        private set

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {
        viewModelScope.launch {
            when (val foodWrapper = getFoodPropertiesUseCase.invoke(searchIngredient)) {
                is FoodWrapper.Success -> {
                    products.clear()
                    products.addAll(foodWrapper.foodProperties.filter { it.description == searchIngredient })
                }

                is FoodWrapper.NoResults -> {
                    products.clear()
                }

                is FoodWrapper.Error -> {
                    // Gérer l'erreur
                }

                FoodWrapper.Loading -> {
                    // Gérer l'état de chargement
                }
            }
        }
    }
}
