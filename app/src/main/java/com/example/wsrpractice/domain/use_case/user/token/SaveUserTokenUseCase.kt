package com.example.wsrpractice.domain.use_case.user.token

import com.example.wsrpractice.data.storage.impl.user.UserStorage

class SaveUserTokenUseCase(private val userStorage: UserStorage) {

    fun execute(token:String):Boolean{
        val result = userStorage.saveToken(token)
        return result
    }

}