package com.example.jajji.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jajji.model.Furniture

@Dao
interface FurDao {
    @Delete
    fun delete(furniture: Furniture)

    @Insert
    fun insert(furniture: Furniture)

    @Query("select * from furniture")
    fun getFurs(): List<Furniture>
}