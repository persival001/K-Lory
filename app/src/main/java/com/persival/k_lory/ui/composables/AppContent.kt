package com.persival.k_lory.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.persival.k_lory.R
import com.persival.k_lory.domain.food_facts.FoodWrapper
import com.persival.k_lory.ui.main.MainViewModel
import com.persival.k_lory.ui.main.MainViewState

@Composable
fun AppContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val manager = LocalFocusManager.current
    val displayableProducts by viewModel.displayableProducts.collectAsState()
    val apiResponse by viewModel.apiResponseFlow.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(
            value = viewModel.searchIngredient,
            onValueChange = viewModel::updateTextFieldValue
        ) {
            viewModel.launchAPI()
            manager.clearFocus()
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when (apiResponse) {
                is FoodWrapper.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is FoodWrapper.Success -> {
                    ProductsList(products = displayableProducts)
                }

                is FoodWrapper.NoResults -> {
                    Text(text = stringResource(id = R.string.no_results), modifier = Modifier.align(Alignment.Center))
                }

                is FoodWrapper.Init -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.kloryicon),
                            contentDescription = "Food Logo",
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                is FoodWrapper.Error -> {
                    Text(
                        text = stringResource(id = R.string.no_results_check_internet),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
    }
}

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, onSearch: () -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onSearch() }),
        label = { Text(text = stringResource(id = R.string.research_of_ingredient)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        trailingIcon = {
            IconButton(onClick = { onSearch() }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    )
}

@Composable
fun ProductsList(products: List<MainViewState>) {
    LazyColumn {
        items(products) { product ->
            ProductCard(mainViewState = product)
        }
    }
}



