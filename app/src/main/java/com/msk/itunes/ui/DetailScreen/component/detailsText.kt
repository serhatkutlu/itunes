package com.msk.itunes.Repository.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun detailsText(key:String,value:String,style:TextStyle){
    Row(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
        Text(key+" :", style =style, fontWeight = FontWeight.Bold )
        Text(value, style =style )
    }
}