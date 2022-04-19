package com.msk.itunes.Util

import android.util.Log
import com.msk.itunes.Responce.Data.SearcResponce.Result
import com.msk.itunes.Responce.Data.SearcResponce.SearchResponce
import com.msk.itunes.Responce.Data.parseData

object WrapperTypeParserClass{

     fun parse(SearchResponce:SearchResponce):kotlin.Result<MutableMap<String,MutableList<Result>>>{
        if (SearchResponce.resultCount<1) return kotlin.Result.failure(Throwable(message = "No result"))
        val list= mutableMapOf("all" to SearchResponce.results)
        SearchResponce.results.forEach {
            if (list.contains(it.wrapperType)){
                list.get(it.wrapperType)?.add(it)
            }else{
                list.put(it.wrapperType, mutableListOf(it))
            }
        }
         return kotlin.Result.success(list)
    }
}