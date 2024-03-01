package com.persival.k_lory.data.food_facts

import com.persival.k_lory.data.food_facts.model.FoodSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDataCentralApi {
    @GET("foods/search")
    suspend fun searchFoods(
        @Query("query") searchTerm: String,
        @Query("api_key") apiKey: String,
    ): Response<FoodSearchResponse>
}