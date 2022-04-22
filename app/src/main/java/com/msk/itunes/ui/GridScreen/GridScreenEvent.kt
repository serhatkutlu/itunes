package com.msk.itunes.ui.GridScreen

sealed class GridScreenEvent{
    data class LoadNewPage(val searchQuery:String,val type:String):GridScreenEvent()
}
