package com.persival.k_lory.domain.food_facts

import kotlinx.coroutines.flow.StateFlow

interface FoodDataCentralRepository {

    suspend fun searchProducts(searchTerm: String): FoodWrapper
}