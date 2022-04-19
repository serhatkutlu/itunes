package com.msk.itunes.Api

import com.msk.itunes.Responce.Data.SearcResponce.SearchResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface itunesApi {

    @GET("/search")
    suspend fun searchItunes(
        @Query("term")
        term:String,
        @Query("limit")
        limit:Int=25,
        @Query("offset")
        offset:Int
    ):SearchResponce

}