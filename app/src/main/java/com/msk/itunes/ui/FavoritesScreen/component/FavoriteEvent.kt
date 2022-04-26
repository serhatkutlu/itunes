package com.msk.itunes.ui.FavoritesScreen.component

sealed class FavoriteEvent {
    object LoadNewPage:FavoriteEvent()
}