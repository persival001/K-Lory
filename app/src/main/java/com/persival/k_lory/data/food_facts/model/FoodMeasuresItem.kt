package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodMeasuresItem(

    @field:SerializedName("measureUnitName")
    val measureUnitName: String? = null,

    @field:SerializedName("disseminationText")
    val disseminationText: String? = null,

    @field:SerializedName("modifier")
    val modifier: String? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("gramWeight")
    val gramWeight: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("measureUnitAbbreviation")
    val measureUnitAbbreviation: String? = null,

    @field:SerializedName("measureUnitId")
    val measureUnitId: Int? = null
)