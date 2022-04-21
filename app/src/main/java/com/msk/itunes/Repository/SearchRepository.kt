package com.msk.itunes.Repository

import com.msk.itunes.Responce.Data.SearcResponce.track.SearchResponce
import com.msk.itunes.Responce.Data.WrapperTypeData
import com.msk.itunes.Util.MediaType
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun  Search(query:String,offset:Int,Mediatype: MediaType): Flow<Result<SearchResponce>>

    }