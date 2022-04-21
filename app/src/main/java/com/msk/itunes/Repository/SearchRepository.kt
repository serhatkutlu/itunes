package com.msk.itunes.Repository

import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun  Search(query:String,offset:Int,type: String): Flow<Result<SearchResponce>>

    }