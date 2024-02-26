package com.persival.k_lory.domain.api.model

data class FoodPropertiesEntity(
    val product_name: String?,
    val brands: String?,
    val energy_kcal_100g: Double?,
    val proteins_100g: Double?,
    val fat_100g: Double?,
    val carbohydrates_100g: Double?,
    val image_url: String?
)