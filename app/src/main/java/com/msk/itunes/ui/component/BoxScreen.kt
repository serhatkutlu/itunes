package com.msk.itunes.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Util.changeImageQuality

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun BoxScreen(result: Result, modifier: Modifier, onclickBox:(Result)->Unit) {
    Column (modifier = modifier.clickable { onclickBox(result) }){
        Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f)) {
            val painter= rememberImagePainter(data=result.artworkUrl100?.changeImageQuality("200x200"))
            when(painter.state){
                is ImagePainter.State.Loading->{
                    Box(modifier= Modifier.fillMaxSize(0.3f)) {
                        CircularProgressIndicator(modifier= Modifier.align(Alignment.Center))
                    }
                }
                is ImagePainter.State.Error->{
                    Image(Icons.Default.Image,contentDescription = "error")
                }
                else->{
                    Image(painter,contentDescription = null,modifier= Modifier.fillMaxWidth(), contentScale = ContentScale.FillHeight)
                }


            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.CenterHorizontally).padding(horizontal = 5.dp)){
            Text(text = result.trackName ?: result.collectionName ?:"", style = MaterialTheme.typography.h6, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 2, modifier = Modifier.align(
                Alignment.Center))
        }

    }
}