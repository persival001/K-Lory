package com.persival.k_lory.ui.main

data class MainViewState(
    val description: String,
    val ingredients: String,
    val servingSize: String,
    val servingSizeUnit: String,
    val energy: String,
    val protein: String,
    val carbohydrate: String,
    val fat: String,
    val showIngredients: Boolean = ingredients.isNotEmpty(),
    val showServingSize: Boolean = servingSize.isNotEmpty(),
    val showEnergy: Boolean = energy != "N/A",
    val showProtein: Boolean = protein != "N/A",
    val showCarbohydrate: Boolean = carbohydrate != "N/A",
    val showFat: Boolean = fat != "N/A",
    val showNutritionalInfo: Boolean = listOf(energy, protein, carbohydrate, fat).any { it != "N/A" }
)

