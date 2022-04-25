package com.msk.itunes.Repository

import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun  Search(query:String,offset:Int,type: String,limit:Int): Flow<Result<SearchResponce>>
    fun SearchId(id:Int):Flow<Result<SearchResponce>>
    }