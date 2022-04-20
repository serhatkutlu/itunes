package com.msk.itunes.Repository

import com.msk.itunes.Responce.Data.WrapperTypeData
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun  Search(query:String,offset:Int): Flow<Result<WrapperTypeData>>

    }