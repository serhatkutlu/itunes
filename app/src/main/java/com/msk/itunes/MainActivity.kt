package com.msk.itunes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.ui.DetailScreen.DetailScreen
import com.msk.itunes.ui.GridScreen.GridScreen
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
                NavHost(navController, startDestination =ituneScreenRoute.SearchScreen.route){
                    composable(route= ituneScreenRoute.SearchScreen.route){
                        SearchScreen(navController)
                    }
                    composable(route= ituneScreenRoute.DetailScreen.route+"/{result}", arguments = listOf(
                        navArgument("result"){
                            type= NavType.IntType
                        }
                    )){

                        DetailScreen(navController,it.arguments!!.getInt("result"))
                    }
                    composable(route= ituneScreenRoute.GridScreen.route+"/{type}/{searchquery}", arguments = listOf(
                        navArgument("type"){
                            type= NavType.StringType
                        },
                        navArgument("searchquery"){
                            type= NavType.StringType
                        }
                    )){
                        val type=it.arguments?.getString("type") ?:"all"
                        val searchquery=it.arguments?.getString("searchquery")?: ""
                        GridScreen(navigation = navController, type = type, searchquery = searchquery)
                    }
                }
            }
            }
        }
    }


