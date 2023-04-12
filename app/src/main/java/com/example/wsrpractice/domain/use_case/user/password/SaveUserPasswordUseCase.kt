package com.example.wsrpractice.domain.use_case.user.password

import com.example.wsrpractice.data.storage.impl.user_password.UserStoragePassword

class SaveUserPasswordUseCase(private val userStoragePassword: UserStoragePassword) {

    fun execute(password:String):Boolean{
        val result = userStoragePassword.savePassword(password = password)
        return result
    }

}