package com.persival.k_lory.data.food_facts

import com.persival.k_lory.data.food_facts.model.FoodNutrientsItem
import com.persival.k_lory.data.food_facts.model.FoodSearchResponse
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun toFoodPropertiesEntities(productResponses: FoodSearchResponse): List<FoodPropertiesEntity> =
        productResponses.foods?.mapNotNull { productItem ->
            productItem?.let {
                val energy = extractNutrientValue(it.foodNutrients, "Energy")
                val protein = extractNutrientValue(it.foodNutrients, "Protein")
                val fat = extractNutrientValue(it.foodNutrients, "Total lipid (fat)")
                val carbohydrate = extractNutrientValue(it.foodNutrients, "Carbohydrate, by difference")

                FoodPropertiesEntity(
                    description = it.description ?: "",
                    ingredients = it.ingredients ?: "",
                    servingSizeUnit = it.servingSizeUnit ?: "",
                    servingSize = it.servingSize as? Double ?: 0.0,
                    energy = energy,
                    protein = protein,
                    fat = fat,
                    carbohydrate = carbohydrate
                )
            }
        } ?: emptyList()

    private fun extractNutrientValue(nutrients: List<FoodNutrientsItem?>?, nutrientName: String): Double? =
        nutrients?.find { it?.nutrientName == nutrientName }?.value as? Double
}