package com.msk.itunes.Util

import java.lang.StringBuilder

fun String.changeImageQuality(string: String):String{
    if (!this.contains("100x100")) return this

    val list=this.split("100x100")
    return StringBuilder()
        .append(list[0])
        .append(string)
        .append(list[1])
        .toString()

}