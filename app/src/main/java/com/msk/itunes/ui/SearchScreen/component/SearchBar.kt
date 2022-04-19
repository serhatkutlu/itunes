package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel

@Composable
fun Searchbar(modifier : Modifier = Modifier, viewModel: SearchScreenViewModel, text: MutableState<String>){

    Box(modifier = modifier) {
        BasicTextField(value = text.value, onValueChange = { text.value=it },  textStyle = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
            ,
            maxLines = 4

        )



    }
}