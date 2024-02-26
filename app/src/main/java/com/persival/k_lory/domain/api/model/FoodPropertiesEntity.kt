package com.persival.k_lory.domain.api.model

data class FoodPropertiesEntity(
    val productName: String?,
    val brands: String?,
    val energyKcal100g: Double?,
    val proteins100g: Double?,
    val fat100g: Double?,
    val carbohydrates100g: Double?,
    val imageUrl: String?
)