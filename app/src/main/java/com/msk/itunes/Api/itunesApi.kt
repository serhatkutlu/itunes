package com.msk.itunes.Api

import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
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
        offset:Int,
        @Query("media")
        media: String
    ): SearchResponce

    @GET("/lookup")
    suspend fun searchid(
        @Query("id")
        id:Int
    ):SearchResponce
}