package com.msk.itunes.ui.DetailScreen

sealed class DetailScreenEvent {
    data class LoadDetail(val id:Int): DetailScreenEvent()
    data class CheckFavorite(val id: Int):DetailScreenEvent()
    data class ClickFavorite(val id: Int):DetailScreenEvent()
}
