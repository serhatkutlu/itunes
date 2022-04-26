package com.msk.moviesapplication.ui.Util

sealed class ituneScreenRoute(val route:String){
    object SearchScreen:ituneScreenRoute("Search_Screen")
    object DetailScreen:ituneScreenRoute("Detail_screen")
    object GridScreen:ituneScreenRoute("Grid_screen")
    object FavoriteScreen:ituneScreenRoute("Favorite_screen")
}
