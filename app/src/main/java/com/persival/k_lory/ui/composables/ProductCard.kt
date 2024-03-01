package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity

@Composable
fun ProductCard(product: FoodPropertiesEntity) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name: ${product.description}")
            Text(text = "Product: ${product.ingredients}")
            Text(text = "Serving size: ${product.servingSize} ${product.servingSizeUnit}")
        }
    }
}
