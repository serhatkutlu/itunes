package com.msk.itunes.Repository

sealed class DetailScreenEvent {
    data class LoadDetail(val id:Int):DetailScreenEvent()
}
