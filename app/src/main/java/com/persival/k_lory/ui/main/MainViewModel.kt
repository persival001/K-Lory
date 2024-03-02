package com.persival.k_lory.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.R
import com.persival.k_lory.domain.food_facts.FoodWrapper
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
) : ViewModel() {

    var searchIngredient: String by mutableStateOf("")
    var products = mutableStateListOf<FoodPropertiesEntity>()
        private set

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun launchAPI() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val foodWrapper = getFoodPropertiesUseCase.invoke(searchIngredient)) {
                is FoodWrapper.Success -> {
                    products.clear()
                    val filteredProducts = foodWrapper.foodProperties.filter {
                        it.description?.contains(searchIngredient, ignoreCase = true) ?: false
                    }
                    products.addAll(filteredProducts)

                    if (filteredProducts.any { it.description.isNullOrEmpty() }) {
                        _toastMessage.value =
                            resourceProvider.getString(R.string.search_no_results_for, searchIngredient)
                    }
                }

                is FoodWrapper.NoResults -> {
                    products.clear()
                    _toastMessage.value = resourceProvider.getString(R.string.search_no_results)
                }

                is FoodWrapper.Error -> {
                    // TODO : Gérer l'erreur
                }

                FoodWrapper.Loading -> {
                    // TODO : Gérer l'état de chargement
                }
            }
            _isLoading.value = false
        }
    }

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun toastShown() {
        _toastMessage.value = null
    }
}

