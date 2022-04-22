package com.msk.itunes.ui.SearchScreen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.msk.itunes.ui.SearchScreen.component.SearchListContentScreen
import com.msk.itunes.ui.SearchScreen.component.Searchbar
import com.msk.moviesapplication.ui.Util.ituneScreenRoute


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val searchstate=viewModel.SearchState.collectAsState()
    val text= remember {
        mutableStateOf("")
    }
    val onclickBox={
    }

    val onClickRow:(String)->Unit={
        navController.navigate(ituneScreenRoute.GridScreen.route+"/$it/${viewModel.searchquery}")
    }

    Column {

        Row {
            Searchbar(viewModel = viewModel,modifier = Modifier
                .weight(8f)
            .padding(16.dp), text = text)

            AnimatedVisibility(
                visible = text.value.isNotBlank(),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ){
                Button(modifier = Modifier.weight(2f).padding(top = 18.dp), onClick = {
                    viewModel.OnEvent(SearchEvent.Searchquery(text.value))
                }){
                    Icon(Icons.Default.Search,contentDescription = "search")

                }
            }


        }

        Box(modifier = Modifier.fillMaxSize()){
            SearchListContentScreen(viewModel, onClickRow = onClickRow,onclickBox=onclickBox)
            if(searchstate.value.isLoading) {
                Box(modifier=Modifier.fillMaxSize(0.2f).align(Alignment.Center)) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).fillMaxSize(), strokeWidth = 7.dp) }
            }
        }


    }


}











