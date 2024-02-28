package com.persival.k_lory.data.food_facts

import android.app.appsearch.SearchResult
import com.persival.k_lory.data.food_facts.model.FoodFactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodFactsApi {
    @GET("api/v2/search")
    suspend fun searchProducts(
        @Query("search_terms") searchTerm: String,
        @Query("fields") fields: String
    ): FoodFactsResponse
}
