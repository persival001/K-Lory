package com.persival.k_lory.domain.food_facts

interface OpenFoodFactsRepository {

    suspend fun searchProducts(searchTerm: String): FoodWrapper
}