package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.persival.k_lory.R
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
                Text(text = stringResource(id = R.string.ingredients, product.ingredients))
            }
            if (!product.servingSizeUnit.isNullOrBlank() && product.servingSize != null) {
                Text(text = stringResource(id = R.string.serving_size, product.servingSize, product.servingSizeUnit))
            }
            Text(text = stringResource(id = R.string.energy, product.energy?.toString() ?: "N/A"))
            Text(text = stringResource(id = R.string.protein, product.protein?.toString() ?: "N/A"))
            Text(text = stringResource(id = R.string.carbohydrate, product.carbohydrate?.toString() ?: "N/A"))
            Text(text = stringResource(id = R.string.fat, product.fat?.toString() ?: "N/A"))
        }
    }
}