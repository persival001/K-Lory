package com.persival.k_lory.data.food_facts.model

import com.google.gson.annotations.SerializedName

data class FoodSearchCriteria(

    @field:SerializedName("pageNumber")
    val pageNumber: Int? = null,

    @field:SerializedName("generalSearchInput")
    val generalSearchInput: String? = null,

    @field:SerializedName("query")
    val query: String? = null,

    @field:SerializedName("numberOfResultsPerPage")
    val numberOfResultsPerPage: Int? = null,

    @field:SerializedName("pageSize")
    val pageSize: Int? = null,

    @field:SerializedName("requireAllWords")
    val requireAllWords: Boolean? = null
)