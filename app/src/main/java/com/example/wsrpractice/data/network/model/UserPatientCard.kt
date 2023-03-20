package com.example.wsrpractice.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserPatientCard(
    val id:Int? = null,
    val firstname:String,
    val middlename:String,
    val lastname:String,
    val bith:String,
    val pol:String,
    val image:String? = null
)