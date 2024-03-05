package com.persival.k_lory.data.food_facts

import com.persival.k_lory.data.food_facts.model.FoodNutrientsItem
import com.persival.k_lory.data.food_facts.model.FoodSearchResponse
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import java.util.Locale
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun toFoodPropertiesEntities(productResponses: FoodSearchResponse): List<FoodPropertiesEntity> =
        productResponses.foods?.mapNotNull { productItem ->
            productItem?.let {
                FoodPropertiesEntity(
                    description = it.description?.capitalizeFully(),
                    ingredients = it.ingredients?.capitalizeFully(),
                    servingSizeUnit = it.servingSizeUnit,
                    servingSize = it.servingSize as? Double,
                    energy = extractNutrientValue(it.foodNutrients, "Energy"),
                    protein = extractNutrientValue(it.foodNutrients, "Protein"),
                    fat = extractNutrientValue(it.foodNutrients, "Total lipid (fat)"),
                    carbohydrate = extractNutrientValue(it.foodNutrients, "Carbohydrate, by difference")
                )
            }
        } ?: emptyList()

    // Extract the nutrient value from the product
    private fun extractNutrientValue(nutrients: List<FoodNutrientsItem?>?, nutrientName: String): Double? =
        nutrients?.find { it?.nutrientName == nutrientName }?.value as? Double

    // Capitalize the first letter and minimize the rest of the string
    private fun String.capitalizeFully(): String =
        split(" ").joinToString(" ") { word ->
            word.lowercase()
                .replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() }
        }

}
