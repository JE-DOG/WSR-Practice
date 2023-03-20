package com.example.wsrpractice.presentetion.ui.model

import android.accounts.AuthenticatorDescription

data class Analezy(
    val id: Int,
    val name: String,
    val description: String,
    val bio: String,
    val category: String,
    val time_result: String,
    val price: String
)
