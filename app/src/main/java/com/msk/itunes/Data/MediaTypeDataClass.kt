package com.msk.itunes.Data

import com.msk.itunes.Responce.Data.SearcResponce.track.Result

data class MediaTypeDataClass(
    val mediaType:String,
    val result: List<Result>
)
