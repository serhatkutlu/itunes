package com.msk.itunes.ui.Util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.msk.moviesapplication.ui.Util.ituneScreenRoute


sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){

    object Search : BottomNavItem("Search", Icons.Default.Search, ituneScreenRoute.SearchScreen.route)
    object Favorite: BottomNavItem("Favorites",Icons.Default.FilterList,ituneScreenRoute.FavoriteScreen.route)

}
