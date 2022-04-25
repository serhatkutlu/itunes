package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msk.itunes.Data.MediaTypeDataClass
import com.msk.itunes.Responce.Data.SearcResponce.track.Result

@Composable
fun MediaTypeRowScreen(mediaTypeDataClass: MediaTypeDataClass, onClickRow:(String)->Unit, onclickBox: (Result) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).clickable { onClickRow(mediaTypeDataClass.mediaType) }){
            Text(mediaTypeDataClass.mediaType, style = MaterialTheme.typography.h4, modifier = Modifier.align(
                Alignment.CenterStart))
            Icon(
                Icons.Default.ArrowForward,contentDescription = null, modifier = Modifier.align(
                Alignment.CenterEnd).size(40.dp))

        }

        HorizontalList(mediaTypeDataClass.result,onclickBox)
    }

}