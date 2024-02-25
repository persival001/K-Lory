package com.persival.k_lory.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.persival.k_lory.data.api_service.OpenFoodFactsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val foodApi: OpenFoodFactsApi
) : ViewModel() {

    // TextField state
    var searchIngredient: String by mutableStateOf("")

    // Call API State
    var apiState: ApiUIState by mutableStateOf(ApiUIState.Loading)

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {
        viewModelScope.launch {
            apiState = ApiUIState.Loading
            try {
                val result = foodApi.searchProducts(searchIngredient, "product_name,brands,nutriments,image_url")
                apiState = ApiUIState.Success(result)
                val filteredResults = result.products.filter {
                    it.product_name?.contains(searchIngredient, ignoreCase = true) ?: false
                }

                if (filteredResults.isNotEmpty()) {
                    filteredResults.forEach { product ->
                        Log.d("FilteredResult", "Produit trouvé: ${product.product_name ?: "Inconnu"}, Marque: ${product.brands ?: "Inconnue"}, Nutriments: " +
                                "${product.nutriments ?: "Non spécifiés"}, Image: ${product.image_url ?: "Pas d'image"}")
                    }
                } else {
                    Log.d("FilteredResult", "Aucun produit correspondant à $searchIngredient trouvé.")
                }

            } catch (exception: Exception) {
                apiState = ApiUIState.Error(exception.message ?: "Erreur inconnue")
                Log.e("MainViewModel", "Exception lors de l'appel API: ${exception.message}")
            }
        }
    }

}