package com.msk.itunes.ui.SearchScreen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import com.msk.itunes.Data.MediaTypeDataClass
import com.msk.itunes.Responce.Data.SearcResponce.track.Result
import com.msk.itunes.Util.changeImageQuality
import com.msk.itunes.ui.SearchScreen.component.Searchbar
import kotlinx.coroutines.flow.collect


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel= hiltViewModel<SearchScreenViewModel>()
    val text= remember {
        mutableStateOf("")
    }
    Column {

        Row {
            Searchbar(viewModel = viewModel,modifier = Modifier
                .weight(8f)
            .padding(16.dp), text = text)

            AnimatedVisibility(
                visible = text.value.isNotBlank(),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ){
                Button(modifier = Modifier.weight(2f).padding(top = 18.dp), onClick = {viewModel.OnEvent(SearchEvent.Searchquery(text.value))}){
                    Icon(Icons.Default.Search,contentDescription = "search")

                }
            }


        }


        SearchListContentScreen(viewModel)


    }


}

@Composable
fun SearchListContentScreen(viewModel: SearchScreenViewModel) {

    val mediaData=viewModel.MediaData.collectAsState()
    val columnstate= rememberLazyListState()
    LazyColumn(modifier = Modifier.fillMaxSize(), state = columnstate) {
        item{
            Spacer(Modifier.height(30.dp))
        }
        items(mediaData.value.size){index ->
            if (mediaData.value[index].result.isNullOrEmpty()) return@items
            MediaTypeRowScreen(mediaData.value[index])
            Spacer(Modifier.height(10.dp))
        }
        item{
            Spacer(Modifier.height(70.dp))
        }

    }

}
@Composable
fun MediaTypeRowScreen(mediaTypeDataClass: MediaTypeDataClass) {
Column(modifier = Modifier.fillMaxWidth().height(300.dp)) {
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)){
        Text(mediaTypeDataClass.mediaType, style = MaterialTheme.typography.h4, modifier = Modifier.align(
            Alignment.CenterStart))
        Icon(Icons.Default.ArrowForward,contentDescription = null, modifier = Modifier.align(
            Alignment.CenterEnd).size(40.dp))

    }
    HorizontalList(mediaTypeDataClass.result)
}

}
@Composable
fun HorizontalList(result: List<Result>) {

    val state=LazyListState()
    LazyRow (state = state){
        items(result.size){item->
            BoxScreen(result[item],Modifier.height(300.dp).width(150.dp).padding(horizontal = 5.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun BoxScreen(result: Result,modifier:Modifier) {
    Column (modifier = modifier){
        Card(shape = AbsoluteRoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f), onClick = {}) {
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
                    Image(painter,contentDescription = null,modifier= Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

                }


            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.CenterHorizontally).padding(horizontal = 5.dp)){
            Text(text = result.trackName ?: result.collectionName ?:"", style = MaterialTheme.typography.h6, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, maxLines = 2, modifier = Modifier.align(Alignment.Center))
        }

    }
}





