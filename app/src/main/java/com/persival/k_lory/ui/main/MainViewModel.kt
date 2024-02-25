package com.persival.k_lory.ui.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.persival.k_lory.data.api_service.FoodAPI
import com.persival.k_lory.data.api_service.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    // TextField state
    var searchIngredient: String by mutableStateOf("")

    // Call API State
    var apiState: ApiUIState by mutableStateOf(ApiUIState.Loading)

    fun updateTextFieldValue(text: String) {
        searchIngredient = text
    }

    fun launchAPI() {
        viewModelScope.launch {
            apiState = ApiUIState.Loading
            try {
                val result = FoodAPI.service.searchProducts(searchIngredient, "product_name,brands,nutriments,image_url")
                // Loguer le résultat ici
                Log.d("MainViewModel", "Réponse de l'API: $result")

                apiState = ApiUIState.Success(forecast = result)
            } catch (iO: IOException) {
                apiState = ApiUIState.Error(error = iO.message ?: "Erreur de connexion")
                Log.e("MainViewModel", "IOException lors de l'appel API: ${iO.message}")
            } catch (http: HttpException) {
                apiState = ApiUIState.Error(error = "Erreur HTTP ${http.code()}: ${http.message()}")
                Log.e("MainViewModel", "HttpException lors de l'appel API: ${http.message}")
            }
        }
    }

    fun convertDatas(string: String): SearchResult {
        val gson = Gson()
        val type = object : TypeToken<SearchResult>() {}.type
        return gson.fromJson(string, type)
    }

}