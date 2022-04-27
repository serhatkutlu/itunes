package com.msk.itunes.Repository.FavoritesRepository

import android.util.Log
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Db.Dao
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesRepositoryImp @Inject constructor( private val dao:Dao):FavoritesRepository {
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




}