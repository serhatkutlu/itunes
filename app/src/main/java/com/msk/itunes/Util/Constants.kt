package com.msk.itunes.Util

object Constants {
    const val BaseUrl=" https://itunes.apple.com"

}

sealed class MediaType( val type:String){
    object movie:MediaType("movie")
    object podcast:MediaType("podcast")
    object music:MediaType("music")
    object musicVideo:MediaType("musicVideo")
    object audiobook:MediaType("audiobook")
    object shortFilm:MediaType("shortFilm")
    object tvShow:MediaType("tvShow")
    object software:MediaType("software")
    object ebook:MediaType("ebook")
    object all:MediaType("all")
}