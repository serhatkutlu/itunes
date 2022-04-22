package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel

@Composable
fun SearchListContentScreen(viewModel: SearchScreenViewModel, onClickRow:(String)->Unit, onclickBox: () -> Unit ) {

    val mediaData=viewModel.MediaData.collectAsState()
    val columnstate= rememberLazyListState()
    LazyColumn(modifier = Modifier.fillMaxSize(), state = columnstate) {
        item{
            Spacer(Modifier.height(30.dp))
        }
        items(mediaData.value.size){index ->
            if (mediaData.value[index].result.isNullOrEmpty()) return@items
            MediaTypeRowScreen(mediaData.value[index], onclickBox = onclickBox, onClickRow = onClickRow)
            Spacer(Modifier.height(10.dp))
        }
        item{
            Spacer(Modifier.height(70.dp))
        }

    }

}