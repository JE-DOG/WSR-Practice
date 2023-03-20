package com.example.wsrpractice.domain.repository

import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity

interface UserRepository {

    suspend fun sendCode(email:String):AnswerServerSendCode

    suspend fun signIn(signInRequestEntity: SIgnInRequestEntity):AnswerServerSignIn

}