package com.persival.k_lory.data.api_service

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

// BaseUrl
private val baseUrl = "https://fr.openfoodfacts.org/"

// Retrofit params
private var scalarsConverterFactory = ScalarsConverterFactory.create()

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(scalarsConverterFactory)
            .build()
    }

    val api: OpenFoodFactsApi by lazy {
        retrofit.create(OpenFoodFactsApi::class.java)
    }
}
