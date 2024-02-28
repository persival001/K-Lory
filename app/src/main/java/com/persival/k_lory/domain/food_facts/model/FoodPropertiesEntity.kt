package com.persival.k_lory.domain.food_facts.model

data class FoodPropertiesEntity(
    // TODO Persival: you don't want all your Entity fields to be nullable (some may, but you need info for an entity to exist)
    val productName: String?,
    val brand: String?,
    val energyKcal100g: Double?,
    val proteins100g: Double?,
    val fat100g: Double?,
    val carbohydrates100g: Double?,
    val imageUrl: String?
)