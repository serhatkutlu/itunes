package com.msk.itunes.ui.DetailScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.msk.itunes.Repository.component.ExoPlayer

@Composable
fun DetailBox(vector:ImageVector,name:String,value:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(vector,contentDescription = null)
        Text(text = name)
        Text(text = value)

    }
}