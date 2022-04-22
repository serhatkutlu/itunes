package com.msk.itunes.ui.GridScreen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.GridScreen.GridScreenEvent
import com.msk.itunes.ui.GridScreen.GridScreenViewmodel
import com.msk.itunes.ui.component.BoxScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGrid(
    data: List<Result>,
    viewModel: GridScreenViewmodel,
    type:String,
    searchquery: String
) {
    val searchstate=viewModel.SearchState.collectAsState()
    LazyVerticalGrid(

        cells = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ) {

        items(data.size) { item ->
            if (item >= data.size - 2 && !searchstate.value.isLoading&&!searchstate.value.endReached) {
                viewModel.OnEvent(GridScreenEvent.LoadNewPage(type=type, searchQuery = searchquery))
            }

            BoxScreen(data[item], Modifier.height(350.dp).width(180.dp).padding(5.dp)){}


        }



    }
}
