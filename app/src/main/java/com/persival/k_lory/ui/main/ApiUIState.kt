package com.persival.k_lory.ui.main

import com.persival.k_lory.data.api_service.model.SearchResult

sealed class ApiUIState {
    object Loading : ApiUIState()
    data class Error(val error: String) : ApiUIState()
    data class Success(val forecast: SearchResult) : ApiUIState()
}