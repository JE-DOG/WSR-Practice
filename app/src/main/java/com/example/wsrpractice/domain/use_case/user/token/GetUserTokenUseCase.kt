package com.example.wsrpractice.domain.use_case.user.token

import com.example.wsrpractice.data.storage.impl.user.UserStorage

class GetUserTokenUseCase(private val userStorage: UserStorage) {

    fun execute():String{
        val token = userStorage.getToken()
        return token
    }

}