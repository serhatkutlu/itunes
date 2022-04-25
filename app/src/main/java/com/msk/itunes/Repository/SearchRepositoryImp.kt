package com.msk.itunes.Repository

import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val api:itunesApi):SearchRepository {

   override fun  Search(query:String,offset:Int,type:String,limit:Int): Flow<Result<SearchResponce>> {

       return flow {
           try {
               val responce=api.searchItunes(query, offset = offset, media =type, limit = limit )
               emit(Result.success(responce))
           }catch (e:Exception){
               emit(Result.failure(e))
           }
       }
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