package com.msk.itunes.Db

import androidx.room.*
import androidx.room.Dao
import com.msk.itunes.Data.Entity.SavedId


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertid(id:SavedId)

    @Query("SELECT * FROM SAVEDID WHERE id LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getIdlist(pageSize:Int, pageIndex:Int):List<SavedId>

    @Query("DELETE FROM SAVEDID WHERE id =:id")
    suspend fun deleteById(id:Int)

    @Query("SELECT * FROM SAVEDID WHERE id =:id")
    suspend fun getId(id:Int):SavedId
}