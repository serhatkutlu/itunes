package com.msk.itunes.Repository.DetailRepository

import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun SearchId(id: Int): Flow<Result<SearchResponce>>
    suspend fun saveid(id:Int)
    suspend fun Checkid(id:Int):Flow<Boolean>

}