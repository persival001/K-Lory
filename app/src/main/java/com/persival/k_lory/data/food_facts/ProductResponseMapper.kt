package com.persival.k_lory.data.food_facts

import com.persival.k_lory.data.food_facts.model.FoodFactsResponse
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun toFoodPropertiesEntities(productResponses: FoodFactsResponse): List<FoodPropertiesEntity> =
        if (!productResponses.products.isNullOrEmpty()) {
            productResponses.products.map {productsItem ->
                if (productsItem == null) return emptyList()
                else
                FoodPropertiesEntity(
                    productName = productsItem.productName ?: "",
                    brand = productsItem.code,
                    imageUrl = productsItem.imageUrl ?: "",
                    nutriments = productsItem.nutriments,
                    //TODO Persival : bon le mapping est cassé pcq l'API n'est pas la bonne pour tes besoins, mais pense à bien null check
                    // tout peut arriver qd on récup des données d'une API


                )
            } else emptyList()
        }
}

