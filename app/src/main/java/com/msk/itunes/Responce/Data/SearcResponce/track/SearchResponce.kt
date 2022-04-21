package com.msk.itunes.Responce.Data.SearcResponce.track


import com.google.gson.annotations.SerializedName

data class SearchResponce(
    @SerializedName("resultCount")
    val resultCount: Int, // 200
    @SerializedName("results")
    val results: MutableList<Result>
)