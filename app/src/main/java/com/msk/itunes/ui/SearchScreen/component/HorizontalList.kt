package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msk.itunes.Data.MediaTypeDataClass
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.component.BoxScreen

@Composable
fun HorizontalList(result: MediaTypeDataClass, onclickBox: (Result, String) -> Unit) {

    val state= rememberLazyListState()
    LazyRow (state = state){
        items(result.result.size){item->
            BoxScreen(result.result[item],type=result.mediaType,
                Modifier.height(300.dp).width(150.dp).padding(horizontal = 5.dp), onclickBox = onclickBox)
        }
    }
}