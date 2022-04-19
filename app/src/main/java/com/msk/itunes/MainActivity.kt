package com.msk.itunes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.msk.itunes.ui.SearchScreen.SearchScreenViewModel
import com.msk.itunes.ui.theme.ItunesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ItunesTheme {
                Greeting("a")
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        val viewModel = hiltViewModel<SearchScreenViewModel>()
        viewModel.get()
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ItunesTheme {
            Greeting("Android")
        }
    }
}