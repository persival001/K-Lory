package com.persival.k_lory.data.food_facts

import com.persival.k_lory.data.food_facts.model.FoodSearchResponse
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun toFoodPropertiesEntities(productResponses: FoodSearchResponse): List<FoodPropertiesEntity> =
        productResponses.foods?.mapNotNull { productItem ->
            productItem?.let {
                FoodPropertiesEntity(
                    description = it.description ?: "",
                    ingredients = it.ingredients ?: "",
                    servingSizeUnit = it.servingSizeUnit ?: "",
                    servingSize = it.servingSize as? Double ?: 0.0,
                    foodNutrients = it.foodNutrients ?: listOf()
                )
            }
        } ?: emptyList()
}

