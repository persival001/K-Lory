package com.persival.k_lory.domain.food_facts

import kotlinx.coroutines.flow.StateFlow

interface FoodDataCentralRepository {

    fun getApiResponseFlow(): StateFlow<FoodWrapper>

    suspend fun searchProducts(searchTerm: String)
}