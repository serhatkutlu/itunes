package com.msk.itunes.ui.FavoritesScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Util.changeImageQuality
import com.msk.itunes.ui.FavoritesScreen.component.FavoriteEvent
import com.msk.moviesapplication.ui.Util.ituneScreenRoute


@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel= hiltViewModel<FavoriteViewModel>()
    val data= viewModel.FavoriteData.collectAsState().value

    val onclick:(SavedId)->Unit={
        navController.navigate(ituneScreenRoute.DetailScreen.route+"/${it.id}/${it.type}")
    }
    LaunchedEffect(true){
        viewModel.OnEvent(FavoriteEvent.LoadNewPage)
    }
    LazyVerticalGrid(

        cells = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ) {
        items(data.size) { item ->
        BoxScreenForFavoriteScreen(data[item],Modifier.height(350.dp).width(180.dp).padding(5.dp), onclick = onclick)

        }

    }
}

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun BoxScreenForFavoriteScreen(SavedID: SavedId, modifier: Modifier,onclick:(SavedId)->Unit) {
    Column (modifier = modifier){
        Box {
            Card(
                shape = AbsoluteRoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f),
                onClick = {onclick(SavedID)}
            ) {
                val painter =
                    rememberImagePainter(data = SavedID.imageUrl.changeImageQuality("200x200"))
                when (painter.state) {
                    is ImagePainter.State.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(0.3f)) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    is ImagePainter.State.Error -> {
                        Image(Icons.Default.Image, contentDescription = "error")
                    }
                    else -> {
                        Image(
                            painter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillHeight
                        )
                    }
                }


            }
            Box(Modifier.align(Alignment.TopEnd).wrapContentSize().background(Color.Black.copy(alpha = 0.7f))){
                Text(text =SavedID.type, color = Color.White, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold )
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.CenterHorizontally).padding(horizontal =6.dp)){
            Text(text = SavedID.name , style = MaterialTheme.typography.h6, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 2, modifier = Modifier.align(
                Alignment.Center))
        }
    }
}