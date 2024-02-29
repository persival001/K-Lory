package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FinalFoodInputFoodsItem(

    @field:SerializedName("unit")
    val unit: String? = null,

    @field:SerializedName("portionDescription")
    val portionDescription: String? = null,

    @field:SerializedName("foodDescription")
    val foodDescription: String? = null,

    @field:SerializedName("rank")
    val rank: Int? = null,

    @field:SerializedName("gramWeight")
    val gramWeight: Int? = null,

    @field:SerializedName("portionCode")
    val portionCode: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("value")
    val value: Any? = null,

    @field:SerializedName("srCode")
    val srCode: Int? = null
)