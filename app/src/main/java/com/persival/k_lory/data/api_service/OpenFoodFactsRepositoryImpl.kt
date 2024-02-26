package com.persival.k_lory.data.api_service

import com.persival.k_lory.domain.api.OpenFoodFactsRepository
import com.persival.k_lory.domain.api.model.FoodPropertiesEntity
import com.persival.k_lory.domain.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenFoodFactsRepositoryImpl @Inject constructor(
    private val openFoodFactsApi: OpenFoodFactsApi,
    private val productResponseMapper: ProductResponseMapper,
    private val coroutineDispatchers: CoroutineDispatchers,
) : OpenFoodFactsRepository {

    override suspend fun searchProducts(searchTerm: String): List<FoodPropertiesEntity>? = withContext(coroutineDispatchers.io) {
        try {
            val searchResult = openFoodFactsApi.searchProducts(
                searchTerm = searchTerm,
                fields = "product_name,brands,nutriments,image_url"
            )
            searchResult.products.map { productResponse ->
                productResponseMapper.toFoodPropertiesEntity(productResponse)
            }
        } catch (e: Exception) {
            // Gestion des exceptions ou log
            null
        }
    }
}
