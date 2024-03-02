package com.persival.k_lory.domain.food_facts

import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

sealed class FoodWrapper {
    data class Success(val foodProperties: List<FoodPropertiesEntity>) : FoodWrapper()
    data object Loading : FoodWrapper()
    data object NoResults : FoodWrapper()
    data object Init : FoodWrapper()
    data class Error(val message: String) : FoodWrapper()
}
