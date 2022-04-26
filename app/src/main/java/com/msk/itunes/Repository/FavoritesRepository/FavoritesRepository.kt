package com.msk.itunes.Repository.FavoritesRepository

import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavoriteId(pageSize:Int,pageIndex:Int): Flow<Result<List<SavedId>>>
    suspend fun deleteFavoriteID(id:Int)
    fun SearchId(id: Int): Flow<Result<SearchResponce>>

}