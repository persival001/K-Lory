package com.persival.k_lory.domain.api

import com.persival.k_lory.domain.api.model.FoodPropertiesEntity

interface OpenFoodFactsRepository {

    suspend fun searchProducts(searchTerm: String): List<FoodPropertiesEntity>?

}