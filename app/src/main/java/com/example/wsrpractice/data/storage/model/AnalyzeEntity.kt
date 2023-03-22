package com.example.wsrpractice.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analyze_table")
data class AnalyzeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val price:String,
    var patients:Int
)