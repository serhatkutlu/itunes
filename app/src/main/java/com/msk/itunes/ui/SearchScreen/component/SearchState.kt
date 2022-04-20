package com.msk.itunes.ui.SearchScreen.component

data class SearchState(
    var isLoading:Boolean=false,
    var trackEndReached:Boolean=false,
    var audiobookEndReached:Boolean=false
)
