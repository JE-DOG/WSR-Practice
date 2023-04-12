package com.example.wsrpractice.data.storage.impl.user

import android.content.Context

class UserStorageImp(context: Context): UserStorage {

    val sharedPreferences = context.getSharedPreferences(
        TOKEN_SHARED_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override fun getToken(): String {
        val token = sharedPreferences.getString(TOKEN_KEY,"Токен не найден") ?: "Токен не найден"
        return token
    }

    override fun saveToken(token: String): Boolean {
        sharedPreferences.edit()
            .putString(TOKEN_KEY,token)
            .apply()
        return true
    }

    companion object{
        const val TOKEN_KEY = "SiniyTraktor"
        const val TOKEN_SHARED_PREFERENCE_NAME = "chereapshki nindza"
    }

}