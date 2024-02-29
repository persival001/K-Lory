package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodNutrientsItem(

    @field:SerializedName("unitName")
    val unitName: String? = null,

    @field:SerializedName("nutrientNumber")
    val nutrientNumber: String? = null,

    @field:SerializedName("foodNutrientSourceDescription")
    val foodNutrientSourceDescription: String? = null,

    @field:SerializedName("derivationId")
    val derivationId: Int? = null,

    @field:SerializedName("derivationDescription")
    val derivationDescription: String? = null,

    @field:SerializedName("percentDailyValue")
    val percentDailyValue: Int? = null,

    @field:SerializedName("foodNutrientSourceCode")
    val foodNutrientSourceCode: String? = null,

    @field:SerializedName("derivationCode")
    val derivationCode: String? = null,

    @field:SerializedName("foodNutrientId")
    val foodNutrientId: Int? = null,

    @field:SerializedName("nutrientId")
    val nutrientId: Int? = null,

    @field:SerializedName("indentLevel")
    val indentLevel: Int? = null,

    @field:SerializedName("foodNutrientSourceId")
    val foodNutrientSourceId: Int? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("nutrientName")
    val nutrientName: String? = null,

    @field:SerializedName("value")
    val value: Any? = null,

    @field:SerializedName("dataPoints")
    val dataPoints: Int? = null,

    @field:SerializedName("max")
    val max: Any? = null,

    @field:SerializedName("min")
    val min: Any? = null,

    @field:SerializedName("median")
    val median: Any? = null
)