package com.msk.itunes.ui.SearchScreen.component

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.msk.itunes.ui.SearchScreen.SearchEvent
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel

@OptIn(ExperimentalFoundationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun GridContent(
    wraperType: Int,
    isopen:Boolean=false,
    function: (Int) -> Unit
){

    val state= rememberLazyListState()
    val viewModel= hiltViewModel<SearchScreenViewModel>()
    val list=viewModel.WrapperData.collectAsState()
    val SearchState=viewModel.SearchState.collectAsState()
    val data= if (wraperType==0) list.value.track else list.value.audiobook
    if (data.isNullOrEmpty()) {
        if (isopen)  viewModel.OnEvent(SearchEvent.LoadPage)
        //if (wraperType==1)Log.d("hatalar","data.size.toString()")

    }
    //if (wraperType==0)Log.d("hatalar","data.size.toString()")
    Box (modifier =Modifier.fillMaxSize().background(Color.Blue)){

    LazyVerticalGrid(

        cells = GridCells.Fixed(2),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ) {


        items(data.size) { item ->

            if (item >= data.size - 2 && !SearchState.value.isLoading && !SearchState.value.endReached&&isopen) {
                viewModel.OnEvent(SearchEvent.LoadPage)
            }

            MovieBoxScreen(
                data.get(item), modifier = Modifier.height(200.dp)
                    .padding(8.dp), cardOnclick = function
            )


        }





        if (SearchState.value.isLoading) {
            item {
                Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.TopCenter) {
                    CircularProgressIndicator(Modifier.fillMaxSize(0.4f).offset(x = 100.dp))

                }
            }

        }


    }

}
}