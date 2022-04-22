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
import com.msk.itunes.ui.GridScreen.component.LazyGrid



@Composable
fun GridScreen(
     navigation:NavController,
     viewmodel: GridScreenViewmodel= hiltViewModel(),
     type:String,
     searchquery: String
) {
    val searchstate=viewmodel.SearchState.collectAsState()
    val datastate=viewmodel.DataState.collectAsState()
    LaunchedEffect(Unit) {
        viewmodel.OnEvent(GridScreenEvent.LoadNewPage(searchquery,type))
    }
    Box (modifier = Modifier.fillMaxSize()){

       LazyGrid(data = datastate.value, viewModel = viewmodel, type = type, searchquery = searchquery)

        if(searchstate.value.isLoading) {
            Box(modifier=Modifier.fillMaxSize(0.2f).align(Alignment.Center)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).fillMaxSize(), strokeWidth = 7.dp) }
        }


}}

