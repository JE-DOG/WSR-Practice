package com.example.wsrpractice.data.storage.impl.user

interface UserStorage {

    fun getToken():String

    fun saveToken(token:String):Boolean

}