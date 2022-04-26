package com.msk.itunes.ui.DetailScreen


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.itunes.Repository.component.ExoPlayer
import com.msk.itunes.Repository.component.detailsText
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Util.changeImageQuality
import com.msk.itunes.Util.dateparser
import com.msk.itunes.ui.DetailScreen.component.DetailBox
import com.msk.itunes.ui.DetailScreen.component.image
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun DetailScreen(id:Int) {
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val result = viewModel.result.collectAsState().value
    LaunchedEffect(true) {
        viewModel.OnEvent(DetailScreenEvent.LoadDetail(id))
    }

    if (result == null) return

    LazyColumn {

        item {
            image(
                result.artworkUrl100?.changeImageQuality("300x300"),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        if (!result.previewUrl.isNullOrBlank()){
            item {
                val height= LocalConfiguration.current.screenHeightDp/2
                ExoPlayer(result.previewUrl,Modifier.fillMaxWidth().height(height.dp))
            }
        }
        if (!result.screenshotUrls.isNullOrEmpty()){
            item{
                screenshots(result.screenshotUrls)
                Spacer(modifier = Modifier.height(7.dp))
                Divider()
                Spacer(modifier = Modifier.height(7.dp))


            }
        }
        if (!result.trackName.isNullOrBlank() || !result.collectionName.isNullOrBlank()) {
        item {
            Box(modifier=Modifier.fillMaxWidth()){
                Text(text = result.trackName ?: result.collectionName ?: "" , style = MaterialTheme.typography.h6, textAlign = TextAlign.Center, modifier = Modifier.align(
                    Alignment.Center))

                }
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(5.dp))

        }
    }
        if (!result.trackViewUrl.isNullOrBlank()||!result.collectionViewUrl.isNullOrBlank()){
            item {
                val context = LocalContext.current
                val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(result.trackViewUrl ?: result.collectionViewUrl))
                Box(Modifier.fillMaxWidth()) {
                    OutlinedButton(onClick = { context.startActivity(webIntent) }, modifier = Modifier.padding(8.dp).align(
                        Alignment.Center)) {
                        Text( text = "OPEN", style = MaterialTheme.typography.h6, fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
            }
        }


        item{
            DetailRow(result)
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(5.dp))

        }

        if (!result.kind.isNullOrBlank()){
            item {
                detailsText(key = "Kind", value =result.kind, style = MaterialTheme.typography.h6 )
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))

            }

        }
        if (!result.primaryGenreName.isNullOrBlank()){
            item {
                detailsText(key = "Genre", value =result.primaryGenreName, style = MaterialTheme.typography.h6 )
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
            }

        }

        if (!result.description.isNullOrBlank() ||!result.shortDescription.isNullOrBlank() || !result.longDescription.isNullOrBlank()) {
            item {
                val text =
                    result.description ?: result.shortDescription ?: result.longDescription ?: ""
                Text("Description : " + text, style = MaterialTheme.typography.h6,modifier=Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun screenshots(screenshotUrls: List<String>) {

    LazyRow {
        items(screenshotUrls.size) {
                Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.height(350.dp).width(180.dp)) {
                    val painter= rememberImagePainter(data=screenshotUrls[it].changeImageQuality("200x200"))
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
            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}
@Composable
fun DetailRow(result:Result){
    Box(modifier = Modifier.fillMaxWidth()){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(
            Alignment.Center)) {
            result.releaseDate?.let {
                DetailBox(vector = Icons.Default.DateRange, name = "release date", value =it.dateparser() )
                Spacer(Modifier.width(20.dp))
            }
            if (result.price!=null||result.collectionPrice!=null) {
                DetailBox(vector = Icons.Default.AttachMoney, name = "price", value =result.price?.toString() ?: result.collectionPrice?.toString() ?: "" )
                Spacer(Modifier.width(20.dp))

            }
            result.trackTimeMillis?.let {
                DetailBox(vector = Icons.Default.AccessTime, name = "duration", value =it.milliseconds.toString() )
                Spacer(Modifier.width(20.dp))

            }
        }
    }
}

