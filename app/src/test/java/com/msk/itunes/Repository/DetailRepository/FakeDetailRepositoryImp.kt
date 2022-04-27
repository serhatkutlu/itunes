package com.msk.itunes.Repository.DetailRepository

import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDetailRepositoryImp : DetailRepository {

    val list= mutableListOf<SavedId>()

    override suspend fun SearchId(id: Int): Flow<Result<SearchResponce>> {

        return flow { emit(Result.success(SearchResponce(0, results =  mutableListOf(com.msk.itunes.Responce.Data.SearcResponce.track.Result(trackId = id))))) }
    }

    override suspend fun saveid(savedId: SavedId) {
        list.add(savedId)
    }

    override suspend fun Checkid(id: Int): Flow<Boolean> {


        return flow {
            list.forEach {
                if (id==it.id) emit(true)
            }
        }
        }

    override suspend fun deleteFavoriteID(id: Int) {
        list.removeIf {
            it.id==id
        }

    }
}