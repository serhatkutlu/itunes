package com.msk.itunes.ui.SearchScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel

@Composable
fun Searchbar(modifier : Modifier = Modifier, viewModel: SearchScreenViewModel, text: MutableState<String>,onSearch:()->Unit){

    Box(modifier = modifier) {
        BasicTextField(value = text.value, onValueChange = { text.value=it },  textStyle = MaterialTheme.typography.h6,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearch() })


            ,modifier = Modifier
                .fillMaxWidth()
                .shadow(30.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
            ,
            maxLines = 4

        )

            Icon(
                Icons.Default.Clear, tint = Color.Black.copy(alpha = 0.7f),
                contentDescription = null, modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp).clickable { text.value="" }
                )


    }
}