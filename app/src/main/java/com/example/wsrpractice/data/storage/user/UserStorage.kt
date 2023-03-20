package com.example.wsrpractice.data.storage.user

interface UserStorage {

    fun getToken():String

    fun saveToken(token:String):Boolean

}