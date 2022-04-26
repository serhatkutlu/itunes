package com.msk.itunes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.DetailScreen.DetailScreen
import com.msk.itunes.ui.GridScreen.GridScreen
import com.msk.itunes.ui.Navigation.BottomNavigation
import com.msk.itunes.ui.Navigation.NavigationGraph
import com.msk.itunes.ui.SearchScreen.SearchScreen
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel
import com.msk.itunes.ui.theme.ItunesTheme
import com.msk.moviesapplication.ui.Util.ituneScreenRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ItunesTheme {
                val navController= rememberNavController()

                Scaffold(bottomBar = {BottomNavigation(navController)}) {
                    NavigationGraph(navController)
                }
            }
            }
        }
    }


