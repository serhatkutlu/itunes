package com.msk.itunes.ui.SearchScreen

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.msk.itunes.ui.SearchScreen.component.Searchbar
import com.msk.itunes.ui.SearchScreen.component.TabScreen


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel= hiltViewModel<SearchScreenViewModel>()
    val text= remember {
        mutableStateOf("")
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
                Button(modifier = Modifier.weight(2f).padding(top = 18.dp), onClick = {viewModel.OnEvent(SearchEvent.Searchquery(text.value))}){
                    Icon(Icons.Default.Search,contentDescription = "search")

                }
            }


        }

        TabScreen()


    }



}





