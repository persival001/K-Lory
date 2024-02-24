package com.persival.k_lory.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.persival.k_lory.ui.main.MainViewModel

@Composable
fun AppScaffold(viewModel: MainViewModel) {
    Scaffold(
        topBar = { Appbar() },
        content = {
            AppContent(modifier = Modifier.padding(it), viewModel = viewModel)
        }
    )
}