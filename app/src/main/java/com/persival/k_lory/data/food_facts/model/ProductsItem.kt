package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("product_name")
    val productName: String? = null
)