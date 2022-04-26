package com.msk.itunes.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "savedID")
data class SavedId(
    @PrimaryKey
    val id:Int
)
