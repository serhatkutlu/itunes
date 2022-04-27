package com.msk.itunes.ui.DetailScreen


import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.msk.itunes.Data.Entity.SavedId
import com.msk.itunes.Repository.component.ExoPlayer
import com.msk.itunes.Repository.component.detailsText
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Util.changeImageQuality
import com.msk.itunes.Util.dateparser
import com.msk.itunes.ui.DetailScreen.component.DetailBox
import com.msk.itunes.ui.DetailScreen.component.image
import com.msk.itunes.ui.component.NoInternetConnectionScreen
import com.msk.itunes.ui.component.WindowInfo
import com.msk.itunes.ui.component.rememberWindowInfo
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun DetailScreen(id:Int,type:String) {
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val result = viewModel.result.collectAsState().value
    val idRegistered=viewModel.idFavorite.collectAsState().value
    val isFailature=viewModel.isFailature.collectAsState().value
    var textstyle=MaterialTheme.typography.h6
    var nametextStyle=MaterialTheme.typography.h5
    var IconSize=40.dp
    val onClick={
        viewModel.OnEvent(DetailScreenEvent.ClickFavorite(
            SavedId(id,type
            , result?.artworkUrl100?.changeImageQuality("300x300")?:"",
            name = result?.trackName ?: result?.collectionName?: "")))
    }

    if (rememberWindowInfo().screenWidthInfo is WindowInfo.WindowType.Compact){
         textstyle=MaterialTheme.typography.h6
         nametextStyle=MaterialTheme.typography.h5

        IconSize=40.dp
    }else{
         textstyle=MaterialTheme.typography.h5
         nametextStyle=MaterialTheme.typography.h4

        IconSize=70.dp
    }

    LaunchedEffect(true) {
        viewModel.OnEvent(DetailScreenEvent.CheckFavorite(id))
        viewModel.OnEvent(DetailScreenEvent.LoadDetail(id))
    }

    if (isFailature){
        NoInternetConnectionScreen({viewModel.OnEvent(DetailScreenEvent.LoadDetail(id)) })
        return
    }
    if (result == null) return

    LazyColumn {

        item {
            Spacer(modifier = Modifier.height(30.dp))

            image(
                result.artworkUrl100?.changeImageQuality("300x300"),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
                if (!result.trackViewUrl.isNullOrBlank() || !result.collectionViewUrl.isNullOrBlank()) {
                    val context = LocalContext.current
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(result.trackViewUrl ?: result.collectionViewUrl)
                    )
                    Icon(
                        Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.clickable { context.startActivity(webIntent) }.size(IconSize))
                }
                val icon=if (idRegistered)Icons.Default.Favorite else Icons.Default.FavoriteBorder
                Icon(icon,contentDescription = null, tint = Color.Red,modifier=Modifier.size(IconSize).clickable { onClick() })
            }
            Spacer(modifier = Modifier.height(10.dp))

            if (!result.previewUrl.isNullOrBlank()){


                ExoPlayer(result.previewUrl,Modifier.fillMaxWidth().height(300.dp))

        }
            Spacer(modifier = Modifier.height(20.dp))

            if (!result.screenshotUrls.isNullOrEmpty()){

                screenshots(result.screenshotUrls)
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(20.dp))

            }
        if (!result.trackName.isNullOrBlank() || !result.collectionName.isNullOrBlank()) {
            Box(modifier=Modifier.fillMaxWidth()){
                Text(text = result.trackName ?: result.collectionName ?: "" , style = nametextStyle, textAlign = TextAlign.Center, modifier = Modifier.align(
                    Alignment.Center), fontWeight = FontWeight.Bold)

                }
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
        }


            DetailRow(result,textstyle)
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(5.dp))



        if (!result.kind.isNullOrBlank()){
                detailsText(key = "Kind", value =result.kind, style = textstyle )
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))



        }
        if (!result.primaryGenreName.isNullOrBlank()){
                detailsText(key = "Genre", value =result.primaryGenreName, style = textstyle )
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(5.dp))


        }

        if (!result.description.isNullOrBlank() ||!result.shortDescription.isNullOrBlank() || !result.longDescription.isNullOrBlank()) {
                val text =
                    result.description ?: result.shortDescription ?: result.longDescription ?: ""
                Text("Description : " + text, style = textstyle,modifier=Modifier.padding(horizontal = 20.dp))

            }
            Spacer(modifier = Modifier.height(80.dp))
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
fun DetailRow(result:Result,style: TextStyle){
    Box(modifier = Modifier.fillMaxWidth()){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(
            Alignment.Center)) {
            result.releaseDate?.let {
                DetailBox(vector = Icons.Default.DateRange, name = "release date", value =it.dateparser(),style=style )
                Spacer(Modifier.width(20.dp))
            }
            if (result.price!=null||result.collectionPrice!=null) {
                DetailBox(vector = Icons.Default.AttachMoney, name = "price", value =result.price?.toString() ?: result.collectionPrice?.toString() ?: "" ,style)
                Spacer(Modifier.width(20.dp))

            }
            result.trackTimeMillis?.let {
                DetailBox(vector = Icons.Default.AccessTime, name = "duration", value =it.milliseconds.toString(),style )
                Spacer(Modifier.width(20.dp))

            }
        }
    }
}

