package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.itunes.Responce.Data.SearcResponce.Result


@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun MovieBoxScreen( result: Result, modifier: Modifier = Modifier, cardOnclick:(Int)->Unit){
    Box(modifier){
        Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.fillMaxSize(), onClick = {}) {
            val painter= rememberImagePainter(data=result.artworkUrl100)
            when(painter.state){
                is ImagePainter.State.Loading->{
                    Box(modifier= Modifier.fillMaxSize(0.3f)) {
                        CircularProgressIndicator(modifier= Modifier.align(Alignment.Center))
                    }
                }
                is ImagePainter.State.Error->{
                    Image(Icons.Default.Movie,contentDescription = "error")
                }
                else->{
                    Image(painter,contentDescription = null,modifier= Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

                }


            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.BottomStart)){
            Text("result.collectionName", style = MaterialTheme.typography.h5, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 1, color = Color.White, modifier = Modifier.align(
                Alignment.Center))
        }



    }
}