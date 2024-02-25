package com.persival.k_lory.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.persival.k_lory.data.api_service.RetrofitInstance
import com.persival.k_lory.data.api_service.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    // TextField state
    var searchIngredient: String by mutableStateOf("")

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun getProducts(): Flow<APICallState<List<Product>>> = flow {
        emit(APICallState.Loading())
        try {
            val response =
                RetrofitInstance.api.searchProducts(searchIngredient, "product_name,brands,nutriments,image_url")
            emit(APICallState.Success(response.products))
            Log.d("APIResponse", "Réponse reçue: ${response.products}")

        } catch (e: Exception) {
            emit(APICallState.Error("Erreur exception : ${e.message ?: "Erreur inconnue"}"))

            Log.e("APIError", "Erreur lors de l'appel API: ${e.message}")

        }
    }

}