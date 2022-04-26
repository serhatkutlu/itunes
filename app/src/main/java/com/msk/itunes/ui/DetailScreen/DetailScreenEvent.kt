package com.msk.itunes.ui.DetailScreen

sealed class DetailScreenEvent {
    data class LoadDetail(val id:Int): DetailScreenEvent()
}
