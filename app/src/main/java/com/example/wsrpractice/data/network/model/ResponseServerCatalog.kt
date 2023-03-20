package com.example.wsrpractice.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseServerCatalog(
    val id:Long,
    val name:String,
    val category: String,
    val price:String,
    val bio:String,
    val description: String,
    val time_result:String,
    val preparation:String,
    var isSelected:Boolean = false
)