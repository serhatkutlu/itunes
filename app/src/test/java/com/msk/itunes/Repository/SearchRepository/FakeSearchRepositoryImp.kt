package com.msk.itunes.Repository.SearchRepository

import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSearchRepositoryImp :SearchRepository {
    override fun Search(
        query: String,
        offset: Int,
        type: String,
        limit: Int
    ): Flow<Result<SearchResponce>> {
        return flow {
            emit(Result.success(SearchResponce(resultCount = 1, results = mutableListOf(com.msk.itunes.Responce.Data.SearcResponce.track.Result(trackId = 5)))))
        }
    }
}