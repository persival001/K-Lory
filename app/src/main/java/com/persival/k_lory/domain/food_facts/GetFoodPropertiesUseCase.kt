package com.persival.k_lory.domain.food_facts

import javax.inject.Inject

class GetFoodPropertiesUseCase @Inject constructor(
    private val foodDataCentralRepository: FoodDataCentralRepository
) {
    suspend fun invoke(searchTerm: String): FoodWrapper =
        foodDataCentralRepository.searchProducts(searchTerm)
}