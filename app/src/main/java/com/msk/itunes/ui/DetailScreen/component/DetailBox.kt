package com.msk.itunes.ui.DetailScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle


@Composable
fun DetailBox(vector:ImageVector,name:String,value:String,style:TextStyle){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(vector,contentDescription = null)
        Text(text = name, style = style)
        Text(text = value, style = style)

    }
}