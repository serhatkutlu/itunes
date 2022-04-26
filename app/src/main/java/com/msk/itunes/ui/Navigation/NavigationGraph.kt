package com.msk.itunes.ui.Navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msk.itunes.ui.DetailScreen.DetailScreen
import com.msk.itunes.ui.FavoritesScreen.FavoriteScreen
import com.msk.itunes.ui.GridScreen.GridScreen
import com.msk.itunes.ui.SearchScreen.SearchScreen
import com.msk.moviesapplication.ui.Util.ituneScreenRoute

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController, startDestination = ituneScreenRoute.SearchScreen.route){
        composable(route= ituneScreenRoute.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(route= ituneScreenRoute.FavoriteScreen.route){
            FavoriteScreen(navController)
        }
        composable(route= ituneScreenRoute.DetailScreen.route+"/{result}/{type}", arguments = listOf(
            navArgument("result"){
                type= NavType.IntType
            }, navArgument("type"){
                type= NavType.StringType
            }
        )){

            DetailScreen(it.arguments!!.getInt("result"),it.arguments?.getString("type") ?: "")
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