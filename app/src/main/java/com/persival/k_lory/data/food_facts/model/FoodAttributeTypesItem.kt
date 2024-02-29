package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodAttributeTypesItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("foodAttributes")
    val foodAttributes: List<FoodAttributesItem?>? = null
)