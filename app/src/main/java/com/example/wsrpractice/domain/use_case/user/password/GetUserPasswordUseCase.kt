package com.example.wsrpractice.domain.use_case.user.password

import com.example.wsrpractice.data.storage.user_password.UserStoragePassword

class GetUserPasswordUseCase(private val userStoragePassword: UserStoragePassword) {

    fun execute():String{
        val password = userStoragePassword.getPassword()
        return password
    }

}