package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

@Composable
fun ProductCard(product: FoodPropertiesEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = product.description ?: "")
            if (!product.ingredients.isNullOrBlank()) {
                Text(text = "Ingredients: ${product.ingredients}")
            }
            if (!product.servingSizeUnit.isNullOrBlank() && product.servingSize != null) {
                Text(text = "Serving size: ${product.servingSize} ${product.servingSizeUnit}")
            }
            Text(text = "Energy: ${product.energy}")
            Text(text = "Protein: ${product.protein}")
            Text(text = "Carbohydrate: ${product.carbohydrate}")
            Text(text = "Fat: ${product.fat}")
        }
    }
}