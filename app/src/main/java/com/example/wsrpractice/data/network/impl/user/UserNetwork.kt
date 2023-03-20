package com.example.wsrpractice.data.network.impl.user

import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity

interface UserNetwork {

    suspend fun sendCode(email: String): AnswerServerSendCode

    suspend fun signIn(sIgnInRequestEntity: SIgnInRequestEntity): AnswerServerSignIn

}