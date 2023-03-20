package com.example.wsrpractice.domain.use_case.user.sign_in

import android.util.Log
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity
import com.example.wsrpractice.domain.repository.UserRepository

class SignInUseCase(private val userRepository: UserRepository) {

    suspend fun execute(
        sIgnInRequestEntity: SIgnInRequestEntity
    ):AnswerServerSignIn{
        val response = userRepository.signIn(
            sIgnInRequestEntity
        )
        Log.d("serverTest","class SignInUseCase:\n${response}")

        return response
    }

}