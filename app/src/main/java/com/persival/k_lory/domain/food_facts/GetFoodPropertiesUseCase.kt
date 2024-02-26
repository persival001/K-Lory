package com.persival.k_lory.domain.food_facts

import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import javax.inject.Inject

class GetFoodPropertiesUseCase @Inject constructor(
    private val openFoodFactsRepository: OpenFoodFactsRepository
) {
    suspend fun invoke(searchTerm: String): List<FoodPropertiesEntity>? {

        val searchResults = openFoodFactsRepository.searchProducts(searchTerm)

        return searchResults?.filter {
            it.productName?.contains(searchTerm, ignoreCase = true) ?: false
        }
    }
}