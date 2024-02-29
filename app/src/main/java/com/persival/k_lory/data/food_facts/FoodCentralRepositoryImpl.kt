package com.persival.k_lory.data.food_facts

import android.util.Log
import com.persival.k_lory.data.food_facts.model.FoodSearchResponse
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.OpenFoodFactsRepository
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import com.persival.k_lory.domain.utils.CoroutineDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodCentralRepositoryImpl @Inject constructor(
    private val foodDataCentralApi: FoodDataCentralApi,
    private val productResponseMapper: ProductResponseMapper,
    private val coroutineDispatchers: CoroutineDispatchers,
) : OpenFoodFactsRepository {

    private val _foodWrapperStateFlow = MutableStateFlow<FoodWrapper>(FoodWrapper.Loading)
    val foodWrapperStateFlow: StateFlow<FoodWrapper> = _foodWrapperStateFlow

    override suspend fun searchProducts(searchTerm: String): FoodWrapper = withContext(coroutineDispatchers.io) {
        _foodWrapperStateFlow.value = FoodWrapper.Loading

        try {
            val searchResponse = foodDataCentralApi.searchFoods(
                query = searchTerm,
                apiKey = API_KEY
            )

            val foods = searchResponse.foods
            if (foods.isNullOrEmpty()) {
                return@withContext FoodWrapper.NoResults
            } else {
                val result: List<FoodPropertiesEntity> = productResponseMapper.toFoodPropertiesEntities(foods.filterNotNull())

                if (result.isEmpty()) {
                    return@withContext FoodWrapper.NoResults
                } else {
                    return@withContext FoodWrapper.Success(result)
                }
            }
        } catch (e: Exception) {
            Log.e("Retrofit", "Exception during API call: ${e.message}")
            return@withContext FoodWrapper.Error(e.message ?: "Unknown error")
        }
    }
}


