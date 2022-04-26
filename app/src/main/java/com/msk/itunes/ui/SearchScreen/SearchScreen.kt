package com.msk.itunes.ui.SearchScreen

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.SearchScreen.component.SearchListContentScreen
import com.msk.itunes.ui.SearchScreen.component.Searchbar
import com.msk.moviesapplication.ui.Util.ituneScreenRoute


@OptIn(ExperimentalPagerApi::class,ExperimentalMaterialApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class
)
@Composable
fun SearchScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val searchstate = viewModel.SearchState.collectAsState()

    val text = remember {
        mutableStateOf("")
    }
    val onclickBox: (Result) -> Unit = {
        val id = it.trackId ?: it.collectionId ?: 1
        navController.navigate(ituneScreenRoute.DetailScreen.route + "/$id")
    }

    val onClickRow: (String) -> Unit = {
        navController.navigate(ituneScreenRoute.GridScreen.route + "/$it/${viewModel.searchquery}")
    }
    val onSearch= {
        viewModel.OnEvent(SearchEvent.Searchquery(text.value))
    }

    if (viewModel.CloseKeyboard.collectAsState().value){
        LocalSoftwareKeyboardController.current?.hide()
    }
    Column {

        Row(modifier = Modifier.height(60.dp).padding(top = 10.dp)) {
            Searchbar(
                viewModel = viewModel, modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp).weight(8f), text = text, onSearch = onSearch
            )


            AnimatedVisibility(
                visible = text.value.isNotBlank(),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Card(modifier = Modifier.weight(2f), shape = CircleShape, onClick =onSearch) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier.background(Color.LightGray.copy(alpha = 0.7f))
                            .padding(5.dp)
                    )

                }
            }


        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            SearchListContentScreen(viewModel, onClickRow = onClickRow, onclickBox = onclickBox)
            if (searchstate.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize(0.2f).align(Alignment.Center)) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                        strokeWidth = 7.dp
                    )
                }
            }
        }


    }


}












