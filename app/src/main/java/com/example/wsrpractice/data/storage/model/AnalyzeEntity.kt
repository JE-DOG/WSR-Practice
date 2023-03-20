package com.example.wsrpractice.data.storage.model

import androidx.room.Entity

@Entity(tableName = "analyze_table")
data class AnalyzeEntity(
    val name:String,
    val price:String,
    var patients:Int
)