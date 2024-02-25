package com.persival.k_lory.data.api_service

import com.persival.k_lory.data.api_service.model.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodFactsApi {
    @GET("api/v2/search")
    fun searchProducts(
        @Query("search_terms") searchTerm: String,
        @Query("fields") fields: String
    ): Call<SearchResult>
}