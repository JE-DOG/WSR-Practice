package com.example.wsrpractice.data.network.model

data class ResponseCreatePatientCard(
    val bith: String,
    val firstname: String,
    val id: Int,
    val image: String,
    val lastname: String,
    val middlename: String,
    val pol: String
)