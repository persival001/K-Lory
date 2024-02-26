package com.persival.k_lory.domain.food_facts

import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

interface OpenFoodFactsRepository {

    suspend fun searchProducts(searchTerm: String): List<FoodPropertiesEntity>?

}