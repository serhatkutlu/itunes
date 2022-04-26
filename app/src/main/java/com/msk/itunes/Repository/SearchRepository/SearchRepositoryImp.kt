package com.msk.itunes.Repository.SearchRepository

import android.util.Log
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val api:itunesApi): SearchRepository {

   override fun  Search(query:String,offset:Int,type:String,limit:Int): Flow<Result<SearchResponce>> {

       return flow {
           try {
               val responce=api.searchItunes(query, offset = offset, media =type, limit = limit )
               emit(Result.success(responce))
           }catch (e:UnknownHostException){
               emit(Result.failure(e))
           }catch (e:Exception){

           }
       }
   }


}