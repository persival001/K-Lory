package com.persival.k_lory.data.food_facts

import com.persival.k_lory.domain.food_facts.FoodDataCentralRepository
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.domain.utils.CoroutineDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodDataCentralRepositoryImpl @Inject constructor(
    private val productResponseMapper: ProductResponseMapper,
    private val coroutineDispatchers: CoroutineDispatchers,
) : FoodDataCentralRepository {

    private val foodDataCentralApi: FoodDataCentralApi = NetworkModule.createFoodDataCentralApi()

    private val _foodWrapperStateFlow = MutableStateFlow<FoodWrapper>(FoodWrapper.Init)
    val foodWrapperStateFlow: StateFlow<FoodWrapper> = _foodWrapperStateFlow

    override suspend fun searchProducts(searchTerm: String) = withContext(coroutineDispatchers.io) {
        _foodWrapperStateFlow.value = FoodWrapper.Loading

        val response = try {
            foodDataCentralApi.searchFoods(searchTerm)
        } catch (e: Exception) {
            _foodWrapperStateFlow.value = FoodWrapper.Error(e.message ?: "Unknown error")
            return@withContext
        }

        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null && !responseBody.foods.isNullOrEmpty()) {
                val result = productResponseMapper.toFoodPropertiesEntities(responseBody)
                _foodWrapperStateFlow.value = if (result.isEmpty()) {
                    FoodWrapper.NoResults
                } else {
                    FoodWrapper.Success(result)
                }
            } else {
                _foodWrapperStateFlow.value = FoodWrapper.NoResults
            }
        } else {
            _foodWrapperStateFlow.value =
                FoodWrapper.Error("API call failed with response code: ${response.code()}")
        }
    }

    override fun getApiResponseFlow(): StateFlow<FoodWrapper> {
        return foodWrapperStateFlow
    }
}


