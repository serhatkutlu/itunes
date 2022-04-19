package com.msk.itunes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msk.itunes.ui.SearchScreen.SearchScreen
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
                    }}
            }
            }
        }
    }


