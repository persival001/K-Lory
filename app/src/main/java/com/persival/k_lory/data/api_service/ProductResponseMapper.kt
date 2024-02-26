package com.persival.k_lory.data.api_service

import com.persival.k_lory.data.api_service.model.Product
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun toFoodPropertiesEntity(productResponse: Product): FoodPropertiesEntity {
        return FoodPropertiesEntity(
            productName = productResponse.product_name,
            brands = productResponse.brands,
            energyKcal100g = productResponse.nutriments?.energy_kcal_100g,
            proteins100g = productResponse.nutriments?.proteins_100g,
            fat100g = productResponse.nutriments?.fat_100g,
            carbohydrates100g = productResponse.nutriments?.carbohydrates_100g,
            imageUrl = productResponse.image_url
        )
    }
}
