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

    var searchIngredient: String by mutableStateOf("")

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {  // TODO Persival: bon bien sûr, LD ou Flow, il vaut mieux que ça soit observé dans la vue
        viewModelScope.launch {
            when (val foodWrapper =
                getFoodPropertiesUseCase.invoke(searchIngredient)) {
                // TODO Persival: tu récup ton wrapper, puis tu traites au cas par cas selon ce qui t'est retourné
                is FoodWrapper.Success -> {
                    foodWrapper.foodProperties.forEach { product ->
                        if (product.description == searchIngredient) {
                            Log.d(
                                "FilteredResult",
                                "Produit trouvé: ${product.description}"
                            )
                        }
                    }
                }

                is FoodWrapper.NoResults -> {
                    // Empty view
                    Log.d("FilteredResult", "Aucun produit correspondant à $searchIngredient trouvé.")
                }

                is FoodWrapper.Error -> {
                    // Error state view
                    Log.e("MainViewModel", "Exception lors de l'appel API: ${foodWrapper.message}")
                }

                FoodWrapper.Loading -> {} // TODO Persival: ptet un loading state ou quoi
            }
        }
    }
}