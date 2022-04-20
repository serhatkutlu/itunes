package com.msk.itunes.Util

import android.util.Log
import com.msk.itunes.Responce.Data.SearcResponce.Result
import com.msk.itunes.Responce.Data.SearcResponce.SearchResponce
import com.msk.itunes.Responce.Data.WrapperTypeData

//this class divides data into lists according to wrapper type

object WrapperTypeParserClass{
      fun parse(SearchResponce:SearchResponce):kotlin.Result<WrapperTypeData>{
        if (SearchResponce.resultCount<1) return kotlin.Result.failure(Throwable(message = "No result"))
        val list=WrapperTypeData()
        SearchResponce.results.forEach {
            if ("track"==it.wrapperType){
                list.track.add(it)
            }else if ("audiobook"==it.wrapperType){
                list.audiobook.add(it)
            }
        }
         return kotlin.Result.success(list)
    }
}