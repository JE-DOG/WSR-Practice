package com.example.wsrpractice.data.network.model

data class UserPatientCardNetwork(
    val id:Int? = null,
    val firstname:String,
    val middlename:String,
    val lastname:String,
    val bith:String,
    val pol:String,
    val image:String? = null
)