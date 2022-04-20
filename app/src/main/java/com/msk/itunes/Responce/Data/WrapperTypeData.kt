package com.msk.itunes.Responce.Data

import com.msk.itunes.Responce.Data.SearcResponce.Result

data class WrapperTypeData(
    val track:MutableList<Result> = mutableListOf(),
    val audiobook:MutableList<Result> = mutableListOf()
)
