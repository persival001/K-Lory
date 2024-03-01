package com.persival.k_lory.domain.food_facts

interface FoodDataCentralRepository {

    suspend fun searchProducts(searchTerm: String): FoodWrapper
}