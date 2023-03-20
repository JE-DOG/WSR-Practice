package com.example.wsrpractice.data.repository

import android.util.Log
import com.example.wsrpractice.data.network.impl.user.UserNetwork
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity
import com.example.wsrpractice.domain.repository.UserRepository

class UserRepositoryImplementation(private val userNetwork: UserNetwork):UserRepository {

    override suspend fun sendCode(email: String): AnswerServerSendCode {
        val response = userNetwork.sendCode(email = email)
        return response
    }

    override suspend fun signIn(signInRequestEntity: SIgnInRequestEntity): AnswerServerSignIn {
        val response = userNetwork.signIn(signInRequestEntity)
        Log.d("serverTest","class UserRepositoryImplementation:\n${response}")
        return response
    }

}