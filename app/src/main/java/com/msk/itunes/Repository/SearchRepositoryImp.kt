package com.msk.itunes.Repository

import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Responce.Data.SearcResponce.SearchResponce
import com.msk.itunes.Responce.Data.WrapperTypeData
import com.msk.itunes.Util.WrapperTypeParserClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val api:itunesApi):SearchRepository {

   override fun  Search(query:String,offset:Int): Flow<Result<WrapperTypeData>> {

       return flow {
           try {
               val responce=api.searchItunes(query, offset = offset)
               val parsedList=WrapperTypeParserClass.parse(responce)
               emit(parsedList)
           }catch (e:Exception){
               emit(Result.failure(e))
           }
       }
   }
}