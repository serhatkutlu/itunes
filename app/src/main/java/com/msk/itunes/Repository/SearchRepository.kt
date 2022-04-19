package com.msk.itunes.Repository

import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun  Search(query:String,offset:Int): Flow<Result<MutableMap<String, MutableList<com.msk.itunes.Responce.Data.SearcResponce.Result>>>>

    }