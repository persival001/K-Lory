package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class Aggregations(

    @field:SerializedName("dataType")
    val dataType: DataType? = null,

    @field:SerializedName("nutrients")
    val nutrients: Nutrients? = null
)