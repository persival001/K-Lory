package com.persival.k_lory.domain.food_facts

import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

sealed class FoodWrapper {
    // TODO Persival: petite classe pratique pour wrapper ta response et la passer à la couche domain/VM
    data class Success(val foodProperties: List<FoodPropertiesEntity>) : FoodWrapper()
    data object Loading : FoodWrapper()
    data object NoResults : FoodWrapper()
    data class Error(val message: String) : FoodWrapper()
}
