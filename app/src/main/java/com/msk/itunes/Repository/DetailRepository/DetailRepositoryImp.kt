package com.msk.itunes.Repository.DetailRepository

import android.util.Log
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Db.Dao
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(private val api: itunesApi,private val dao:Dao):DetailRepository {
    override suspend fun SearchId(id: Int): Flow<Result<SearchResponce>> {
        return flow {
            try {
                val responce=api.searchid(id)
                emit(Result.success(responce))
            }catch (e:Exception){
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun saveid(id:Int){
        val savedId=SavedId(id)
        dao.insertid(savedId)
    }

   override suspend fun Checkid(id:Int):Flow<Boolean> = flow {
        try {
            val istrue=dao.getId(id)
            emit(true)
        }catch (e:Exception){
            emit(false)
        }
    }

    override suspend fun deleteFavoriteID(id: Int) {
        dao.deleteById(id)
    }
}