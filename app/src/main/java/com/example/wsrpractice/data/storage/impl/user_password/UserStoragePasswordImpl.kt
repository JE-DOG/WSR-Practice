package com.example.wsrpractice.data.storage.impl.user_password

import android.content.Context

class UserStoragePasswordImpl(context: Context): UserStoragePassword {
    private val sharedPreferences = context.getSharedPreferences(
        PASSWORD_SHARED_PREF_NAME,
        Context.MODE_PRIVATE
    )

    override fun getPassword(): String {
        val password = sharedPreferences.getString(PASSWORD_KEY,"") ?: ""
        return password
    }

    override fun savePassword(password: String): Boolean {
        sharedPreferences.edit()
            .putString(PASSWORD_KEY,password)
            .apply()
        return true
    }

    companion object{

        const val PASSWORD_KEY = "LALDJFAKLSF"
        const val PASSWORD_SHARED_PREF_NAME = "HAISENBERG"

    }
}