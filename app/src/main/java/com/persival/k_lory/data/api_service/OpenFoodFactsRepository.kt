package com.persival.k_lory.data.api_service

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

// BaseUrl
private val baseUrl = "https://fr.openfoodfacts.org/"

// Retrofit params
private var scalarsConverterFactory = ScalarsConverterFactory.create()

// Retrofit construction
private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(scalarsConverterFactory)
    .baseUrl(baseUrl)
    .build()

object OpenFoodFactsAPI {
    val service: OpenFoodFactsApi by lazy {
        retrofit.create(OpenFoodFactsApi::class.java)
    }
}