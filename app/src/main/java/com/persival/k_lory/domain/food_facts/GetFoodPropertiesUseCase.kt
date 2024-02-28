package com.persival.k_lory.domain.food_facts

import javax.inject.Inject

class GetFoodPropertiesUseCase @Inject constructor(
    private val openFoodFactsRepository: OpenFoodFactsRepository
) {
    suspend fun invoke(searchTerm: String): FoodWrapper =
        openFoodFactsRepository.searchProducts(searchTerm)
}