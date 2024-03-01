package com.persival.k_lory.data.food_facts

import android.util.Log
import com.persival.k_lory.BuildConfig
import com.persival.k_lory.domain.food_facts.FoodDataCentralRepository
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import com.persival.k_lory.domain.utils.CoroutineDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FoodDataCentralRepositoryImpl @Inject constructor(
    private val foodDataCentralApi: FoodDataCentralApi,
    private val productResponseMapper: ProductResponseMapper,
    private val coroutineDispatchers: CoroutineDispatchers,
) : FoodDataCentralRepository {

    private val _foodWrapperStateFlow = MutableStateFlow<FoodWrapper>(FoodWrapper.Loading)
    val foodWrapperStateFlow: StateFlow<FoodWrapper> = _foodWrapperStateFlow

    override suspend fun searchProducts(searchTerm: String): FoodWrapper = withContext(coroutineDispatchers.io) {
        _foodWrapperStateFlow.value = FoodWrapper.Loading

        try {
            val searchResponse = foodDataCentralApi.searchFoods(searchTerm, BuildConfig.API_KEY)

            if (searchResponse.isSuccessful) {
                val responseBody = searchResponse.body()

                if (responseBody != null && !responseBody.foods.isNullOrEmpty()) {
                    val result: List<FoodPropertiesEntity> =
                        productResponseMapper.toFoodPropertiesEntities(responseBody)

                    return@withContext if (result.isEmpty()) {
                        FoodWrapper.NoResults
                    } else {
                        FoodWrapper.Success(result)
                    }
                } else {
                    return@withContext FoodWrapper.NoResults
                }
            } else {
                Log.e("Retrofit", "API call failed with response code: ${searchResponse.code()}")
                return@withContext FoodWrapper.Error("API call failed with response code: ${searchResponse.code()}")
            }
        } catch (e: Exception) {
            Log.e("Retrofit", "Exception during API call: ${e.message}")
            return@withContext FoodWrapper.Error(e.message ?: "Unknown error")
        }
    }

}


