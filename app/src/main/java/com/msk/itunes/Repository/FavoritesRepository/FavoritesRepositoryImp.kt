package com.msk.itunes.Repository.FavoritesRepository

import android.util.Log
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Db.Dao
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesRepositoryImp @Inject constructor( private val dao:Dao,private val api: itunesApi):FavoritesRepository {
      override suspend fun getFavoriteId(): Flow<Result<List<SavedId>>> {
         return flow {
            try {
                val result=dao.getIdlist()
                emit( Result.success(result))
            }catch (e:Exception){
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun deleteFavoriteID(id: Int) {
        dao.deleteById(id)
    }

    override fun SearchId(id: Int): Flow<Result<SearchResponce>> {
        return flow {
            try {
                val responce=api.searchid(id)
                emit(Result.success(responce))
            }catch (e:Exception){
                emit(Result.failure(e))
            }
        }
    }

}