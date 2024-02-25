package com.persival.k_lory.data.api_service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// BaseUrl
private val baseUrl = "https://fr.openfoodfacts.org/"

// Retrofit construction
private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

object FoodAPI {
    val service: OpenFoodFactsApi by lazy {
        retrofit.create(OpenFoodFactsApi::class.java)
    }
}