package com.msk.itunes.Repository.FavoritesRepository

import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

class FakeFavoritesRepositoryImp:FavoritesRepository{

    val list= listOf(SavedId(id = 8, type = "", imageUrl = "", name = ""))

    override suspend fun getFavoriteId(): Flow<Result<List<SavedId>>> {
        return flow {
            emit(Result.success(list))
        }
    }




}