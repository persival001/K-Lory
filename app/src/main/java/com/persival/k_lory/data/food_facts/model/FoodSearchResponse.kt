package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodSearchResponse(

    @field:SerializedName("foodSearchCriteria")
    val foodSearchCriteria: FoodSearchCriteria? = null,

    @field:SerializedName("foods")
    val foods: List<FoodsItem?>? = null,

    @field:SerializedName("totalHits")
    val totalHits: Int? = null,

    @field:SerializedName("totalPages")
    val totalPages: Int? = null,

    @field:SerializedName("pageList")
    val pageList: List<Int?>? = null,

    @field:SerializedName("currentPage")
    val currentPage: Int? = null,

    @field:SerializedName("aggregations")
    val aggregations: Aggregations? = null
)