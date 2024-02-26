package com.persival.k_lory.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.GetFoodPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFoodPropertiesUseCase: GetFoodPropertiesUseCase,
) : ViewModel() {

    // TextField state
    var searchIngredient: String by mutableStateOf("")

    // Call API State
    private var apiState: FoodWrapper by mutableStateOf(FoodWrapper.Loading)

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {
        viewModelScope.launch {
            apiState = FoodWrapper.Loading
            try {
                val filteredResults = getFoodPropertiesUseCase.invoke(searchIngredient)
                if (filteredResults.isNullOrEmpty()) {
                    Log.d("FilteredResult", "Aucun produit correspondant à $searchIngredient trouvé.")
                    apiState = FoodWrapper.Error("Aucun produit trouvé")
                } else {
                    apiState = FoodWrapper.Success(filteredResults)
                    filteredResults.forEach { product ->
                        Log.d(
                            "FilteredResult",
                            "Produit trouvé: ${product.productName ?: "Inconnu"}, Marque: ${product.brands ?: "Inconnue"}, Nutriments: " +
                                    "${product.carbohydrates100g ?: "Non spécifiés"}, Image: ${product.imageUrl ?: "Pas d'image"}"
                        )
                    }
                }
            } catch (exception: Exception) {
                apiState = FoodWrapper.Error(exception.message ?: "Erreur inconnue")
                Log.e("MainViewModel", "Exception lors de l'appel API: ${exception.message}")
            }
        }
    }

}