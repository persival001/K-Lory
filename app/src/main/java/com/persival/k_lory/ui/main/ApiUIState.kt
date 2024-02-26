package com.persival.k_lory.ui.main

import com.persival.k_lory.domain.api.model.FoodPropertiesEntity

sealed class ApiUIState {
    object Loading : ApiUIState()
    data class Success(val products: List<FoodPropertiesEntity>) : ApiUIState()
    data class Error(val message: String) : ApiUIState()
}
