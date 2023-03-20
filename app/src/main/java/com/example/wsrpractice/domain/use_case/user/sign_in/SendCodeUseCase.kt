package com.example.wsrpractice.domain.use_case.user.sign_in

import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.domain.repository.UserRepository

class SendCodeUseCase(private val userRepository: UserRepository) {

    suspend fun execute(email:String):AnswerServerSendCode{
        val response = userRepository.sendCode(email)
        return response
    }

}