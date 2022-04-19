package com.msk.itunes.Util

import com.msk.itunes.Responce.Data.SearcResponce.Result
import com.msk.itunes.Responce.Data.SearcResponce.SearchResponce

//this class divides data into lists according to wrapper type

object WrapperTypeParserClass{
     fun parse(SearchResponce:SearchResponce):kotlin.Result<MutableMap<String,MutableList<Result>>>{
        if (SearchResponce.resultCount<1) return kotlin.Result.failure(Throwable(message = "No result"))
        val list:MutableMap<String,MutableList<Result>> = mutableMapOf()
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