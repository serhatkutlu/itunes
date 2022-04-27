package com.msk.itunes.ui.Navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.msk.itunes.R
import com.msk.itunes.ui.Util.BottomNavItem
import com.msk.itunes.ui.component.WindowInfo
import com.msk.itunes.ui.component.rememberWindowInfo
import com.msk.moviesapplication.ui.Util.ituneScreenRoute

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Search,
        BottomNavItem.Favorite,

    )
    var textstyle=MaterialTheme.typography.body1
    var ıconsıze=30.dp
    if (rememberWindowInfo().screenWidthInfo is WindowInfo.WindowType.Compact){
        textstyle= MaterialTheme.typography.body1
        ıconsıze=20.dp
    }else{
        textstyle= MaterialTheme.typography.h6
        ıconsıze=30.dp
    }
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title, modifier = Modifier.size(ıconsıze)) },
                label = { Text(text = item.title,
                    style = textstyle) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}