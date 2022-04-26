package com.msk.itunes.ui.GridScreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.GridScreen.component.LazyGrid
import com.msk.itunes.ui.component.NoInternetConnectionScreen
import com.msk.moviesapplication.ui.Util.ituneScreenRoute


@Composable
fun GridScreen(
     navigation:NavController,
     viewmodel: GridScreenViewmodel= hiltViewModel(),
     type:String,
     searchquery: String
) {
    val searchstate=viewmodel.SearchState.collectAsState()
    val datastate=viewmodel.DataState.collectAsState()

    val onClickBox:(Result,String)->Unit={it,type->
        val id=it.trackId ?: it.collectionId ?: 1
        navigation.navigate(ituneScreenRoute.DetailScreen.route+"/$id/$type")
    }
    LaunchedEffect(Unit) {
        viewmodel.OnEvent(GridScreenEvent.LoadNewPage(searchquery,type))
    }
    if(searchstate.value.isFailature==true){
        NoInternetConnectionScreen {
            viewmodel.OnEvent(GridScreenEvent.LoadNewPage(searchquery,type))

        }
    }
    Box (modifier = Modifier.fillMaxSize()){

       LazyGrid(data = datastate.value, viewModel = viewmodel, type = type, searchquery = searchquery, onclikBox = onClickBox)

        if(searchstate.value.isLoading) {
            Box(modifier=Modifier.fillMaxSize(0.2f).align(Alignment.Center)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).fillMaxSize(), strokeWidth = 7.dp) }
        }


}}

