package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.persival.k_lory.ui.main.MainViewState

@Composable
fun ProductCard(mainViewState: MainViewState) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = mainViewState.description)
            if (mainViewState.showNutritionalInfo) {

                if (mainViewState.showIngredients) {
                    Text(text = "Ingrédients: ${mainViewState.ingredients}")
                }

                if (mainViewState.showServingSize) {
                    Text(text = "Serving size: ${mainViewState.servingSize} ${mainViewState.servingSizeUnit}")
                }

                if (mainViewState.showEnergy) {
                    Text(text = "Energy: ${mainViewState.energy}")
                }

                if (mainViewState.showProtein) {
                    Text(text = "Protein: ${mainViewState.protein}")
                }

                if (mainViewState.showCarbohydrate) {
                    Text(text = "Carbohydrate: ${mainViewState.carbohydrate}")
                }

                if (mainViewState.showFat) {
                    Text(text = "Fat: ${mainViewState.fat}")
                }

            }
        }
    }
}
