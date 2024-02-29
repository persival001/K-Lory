package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodsItem(

    @field:SerializedName("foodNutrients")
    val foodNutrients: List<FoodNutrientsItem?>? = null,

    @field:SerializedName("dataType")
    val dataType: String? = null,

    @field:SerializedName("ndbNumber")
    val ndbNumber: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("allHighlightFields")
    val allHighlightFields: String? = null,

    @field:SerializedName("fdcId")
    val fdcId: Int? = null,

    @field:SerializedName("finalFoodInputFoods")
    val finalFoodInputFoods: List<Any?>? = null,

    @field:SerializedName("score")
    val score: Any? = null,

    @field:SerializedName("commonNames")
    val commonNames: String? = null,

    @field:SerializedName("microbes")
    val microbes: List<Any?>? = null,

    @field:SerializedName("foodMeasures")
    val foodMeasures: List<Any?>? = null,

    @field:SerializedName("additionalDescriptions")
    val additionalDescriptions: String? = null,

    @field:SerializedName("foodAttributeTypes")
    val foodAttributeTypes: List<Any?>? = null,

    @field:SerializedName("publishedDate")
    val publishedDate: String? = null,

    @field:SerializedName("foodVersionIds")
    val foodVersionIds: List<Any?>? = null,

    @field:SerializedName("foodCategory")
    val foodCategory: String? = null,

    @field:SerializedName("foodAttributes")
    val foodAttributes: List<Any?>? = null,

    @field:SerializedName("foodCategoryId")
    val foodCategoryId: Int? = null,

    @field:SerializedName("foodCode")
    val foodCode: Int? = null,

    @field:SerializedName("scientificName")
    val scientificName: String? = null,

    @field:SerializedName("mostRecentAcquisitionDate")
    val mostRecentAcquisitionDate: String? = null,

    @field:SerializedName("marketCountry")
    val marketCountry: String? = null,

    @field:SerializedName("brandName")
    val brandName: String? = null,

    @field:SerializedName("tradeChannels")
    val tradeChannels: List<String?>? = null,

    @field:SerializedName("brandOwner")
    val brandOwner: String? = null,

    @field:SerializedName("servingSizeUnit")
    val servingSizeUnit: String? = null,

    @field:SerializedName("gtinUpc")
    val gtinUpc: String? = null,

    @field:SerializedName("modifiedDate")
    val modifiedDate: String? = null,

    @field:SerializedName("ingredients")
    val ingredients: String? = null,

    @field:SerializedName("dataSource")
    val dataSource: String? = null,

    @field:SerializedName("servingSize")
    val servingSize: Any? = null,

    @field:SerializedName("packageWeight")
    val packageWeight: String? = null,

    @field:SerializedName("householdServingFullText")
    val householdServingFullText: String? = null
)