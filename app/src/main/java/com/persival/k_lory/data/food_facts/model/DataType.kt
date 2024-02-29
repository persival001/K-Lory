package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class DataType(

    @field:SerializedName("Branded")
    val branded: Int? = null,

    @field:SerializedName("Foundation")
    val foundation: Int? = null,

    @field:SerializedName("SR Legacy")
    val sRLegacy: Int? = null,

    @field:SerializedName("Survey (FNDDS)")
    val surveyFNDDS: Int? = null
)