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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
) : ViewModel() {

    var searchIngredient: String by mutableStateOf("")
    var products = mutableStateListOf<FoodPropertiesEntity>()
        private set

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {
        viewModelScope.launch {
            when (val foodWrapper = getFoodPropertiesUseCase.invoke(searchIngredient)) {
                is FoodWrapper.Success -> {
                    products.clear()
                    val filteredProducts = foodWrapper.foodProperties.filter { it.description == searchIngredient }
                    products.addAll(filteredProducts)

                    if (filteredProducts.any { it.description.isNullOrEmpty() }) {
                        _toastMessage.value = "Aucun élément trouvé pour \"$searchIngredient\""
                    }
                }

                is FoodWrapper.NoResults -> {
                    products.clear()
                    _toastMessage.value = "Aucun élément trouvé."
                }

                is FoodWrapper.Error -> {
                    // TODO : Gérer l'erreur
                }

                FoodWrapper.Loading -> {
                    // TODO : Gérer l'état de chargement
                }
            }
        }
    }

    fun toastShown() {
        _toastMessage.value = null
    }
}

