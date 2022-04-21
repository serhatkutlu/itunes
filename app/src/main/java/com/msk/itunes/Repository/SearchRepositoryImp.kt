package com.msk.itunes.Repository

import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import com.msk.itunes.Responce.Data.WrapperTypeData
import com.msk.itunes.Util.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val api:itunesApi):SearchRepository {

   override fun  Search(query:String,offset:Int,Mediatype:MediaType): Flow<Result<SearchResponce>> {

       return flow {
           try {
               val responce=api.searchItunes(query, offset = offset, media =Mediatype.type )
               emit(Result.success(responce))
           }catch (e:Exception){
               emit(Result.failure(e))
           }
       }
   }
}