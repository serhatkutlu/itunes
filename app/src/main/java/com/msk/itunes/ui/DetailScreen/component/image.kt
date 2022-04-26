package com.msk.itunes.ui.DetailScreen.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation


@OptIn(ExperimentalCoilApi::class)
@Composable
fun image(url:String?,modifier: Modifier = Modifier){
    var imageHeight:Double
    if (LocalConfiguration.current.orientation== Configuration.ORIENTATION_PORTRAIT){
        imageHeight= LocalConfiguration.current.screenHeightDp/2.5
    }else{
        imageHeight= LocalConfiguration.current.screenHeightDp/1.2

    }
    val imagewidth= if(LocalConfiguration.current.screenWidthDp<840) 0.6f else 0.4f
    Log.d("hata", LocalConfiguration.current.screenHeightDp.toString())
    Log.d("hata", LocalConfiguration.current.screenWidthDp.toString())
    val painter= rememberImagePainter(data = url, builder = {
        transformations(RoundedCornersTransformation(10f))
    })
    when(painter.state){
        is ImagePainter.State.Loading->{
            CircularProgressIndicator()
        }
        is ImagePainter.State.Error->{}
        else->{
            Box(modifier = modifier.height(imageHeight.dp).fillMaxWidth()) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(imagewidth).align(Alignment.Center)
                )

            }
        }
    }

}

