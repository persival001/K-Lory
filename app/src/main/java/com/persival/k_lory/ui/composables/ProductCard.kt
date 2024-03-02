package com.persival.k_lory.ui.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.persival.k_lory.domain.food_facts.model.FoodPropertiesEntity
import com.persival.k_lory.ui.main.MainViewModel

@Composable
fun ProductCard(product: FoodPropertiesEntity, viewModel: MainViewModel) {
    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsState()

    toastMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.toastShown()
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Name: ${product.description}")
            Text(text = "Ingredients: ${product.ingredients}")
            Text(text = "Serving size: ${product.servingSize} ${product.servingSizeUnit}")
            Text(text = "Energy: ${product.energy}")
            Text(text = "Protein: ${product.protein}")
            Text(text = "Carbohydrate: ${product.carbohydrate}")
            Text(text = "Fat: ${product.fat}")
        }
    }
}