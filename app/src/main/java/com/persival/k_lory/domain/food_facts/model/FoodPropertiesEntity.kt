package com.persival.k_lory.domain.food_facts.model

import com.persival.k_lory.data.food_facts.model.FoodNutrientsItem

data class FoodPropertiesEntity(
    val description: String?,
    val ingredients: String?,
    val servingSizeUnit: String?,
    val servingSize: Double?,
    val foodNutrients: List<FoodNutrientsItem?>,
)