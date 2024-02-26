package com.persival.k_lory.domain.food_facts

import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

sealed class FoodWrapper {
    object Loading : FoodWrapper()
    data class Success(val products: List<FoodPropertiesEntity>) : FoodWrapper()
    data class Error(val message: String) : FoodWrapper()
}
