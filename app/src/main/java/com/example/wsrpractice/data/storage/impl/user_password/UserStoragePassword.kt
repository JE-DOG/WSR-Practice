package com.example.wsrpractice.data.storage.impl.user_password

interface UserStoragePassword{

    fun getPassword():String

    fun savePassword(password:String):Boolean

}