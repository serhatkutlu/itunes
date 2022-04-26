package com.msk.itunes.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msk.itunes.Data.Entity.SavedId


@Database(entities = [SavedId::class], version = 1)
abstract class itunesDatabase :RoomDatabase(){

    abstract val dao:Dao
}