package com.persival.k_lory.domain.food_facts.model

data class FoodPropertiesEntity(
    val description: String?,
    val ingredients: String?,
    val servingSizeUnit: String?,
    val servingSize: Double?,
    val energy: Double?,
    val protein: Double?,
    val fat: Double?,
    val carbohydrate: Double?
)