package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodFactsResponse(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("skip")
    val skip: Int? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("page_count")
    val pageCount: Int? = null,

    @field:SerializedName("page_size")
    val pageSize: Int? = null,

    @field:SerializedName("products")
    val products: List<ProductsItem?>? = null
)