package com.example.jajji.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Furniture(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var image: Int,
    var costs: String
) :Serializable{
}