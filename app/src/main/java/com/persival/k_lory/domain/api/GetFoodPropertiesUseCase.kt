package com.persival.k_lory.domain.api

import com.persival.k_lory.domain.api.model.FoodPropertiesEntity
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