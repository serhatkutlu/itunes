package com.msk.itunes.ui.DetailScreen

import com.msk.itunes.Data.Entity.SavedId

sealed class DetailScreenEvent {
    data class LoadDetail(val id:Int): DetailScreenEvent()
    data class CheckFavorite(val id: Int):DetailScreenEvent()
    data class ClickFavorite(val SavedId:SavedId):DetailScreenEvent()
}
