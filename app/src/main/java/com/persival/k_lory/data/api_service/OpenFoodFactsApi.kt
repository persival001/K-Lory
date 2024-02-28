package com.persival.k_lory.data.api_service

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodFactsApi {
    @GET("api/v2/search")
    suspend fun searchProducts(
        @Query("search_terms") searchTerm: String,
        @Query("fields") fields: String
    ): SearchResult
}
