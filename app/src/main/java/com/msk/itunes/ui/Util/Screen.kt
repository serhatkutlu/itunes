package com.msk.moviesapplication.ui.Util

sealed class ituneScreenRoute(val route:String){
    object SearchScreen:ituneScreenRoute("Search_Screen")
    object DetailScreen:ituneScreenRoute("DetailScreen_screen")
}
