package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.persival.k_lory.ui.main.MainViewModel

@Composable
fun AppScaffold(viewModel: MainViewModel) {
    Scaffold(
        topBar = { Appbar() },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                AppContent(viewModel = viewModel)

                LazyColumn {
                    items(items = viewModel.products.value) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }
    )
}

