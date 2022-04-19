package com.msk.itunes.ui.SearchScreen

sealed class SearchEvent {
    object LoadPage:SearchEvent()
    data class Searchquery(val query:String):SearchEvent()
}