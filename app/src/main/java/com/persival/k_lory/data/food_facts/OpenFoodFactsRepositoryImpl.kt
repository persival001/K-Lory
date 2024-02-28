package com.persival.k_lory.data.food_facts

import android.util.Log
import com.persival.k_lory.data.food_facts.model.FoodFactsResponse
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.OpenFoodFactsRepository
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
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

    override suspend fun searchProducts(searchTerm: String): FoodWrapper = withContext(coroutineDispatchers.io) {
        try {
            FoodWrapper.Loading

            val searchResponse: FoodFactsResponse = openFoodFactsApi.searchProducts(
                searchTerm = searchTerm,
                fields = "product_name,brands,nutriments,image_url"
            )
            if (searchResponse.products.isNullOrEmpty()) FoodWrapper.NoResults

            val result: List<FoodPropertiesEntity> =
                productResponseMapper.toFoodPropertiesEntities(searchResponse)

            // TODO Persival : cas où on a bien reçu une response non nulle,
            // mais vide ou champs exigés nulls donc on a renvoyé du null (à faire selon tes exigences)
            if (result.isEmpty()) FoodWrapper.NoResults

            else FoodWrapper.Success(result)
        } catch (e: Exception) {
            Log.e("Retrofit", "Exception during API call: ${e.message}")
            FoodWrapper.Error(e.message ?: "Unknown error")
        }
    }
}
