package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodAttributesItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("sequenceNumber")
    val sequenceNumber: Int? = null
)