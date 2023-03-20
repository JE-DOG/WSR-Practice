package com.example.wsrpractice.data.network.impl.user

import android.util.Log
import com.example.wsrpractice.data.network.Network
import com.example.wsrpractice.data.network.impl.user.UserNetwork
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity

class UserNetworkImplementation: UserNetwork {

    private val api = Network.apiUser

    override suspend fun sendCode(email: String): AnswerServerSendCode {
        val response = api.sendCode(email = email)
        return response

    }

    override suspend fun signIn(sIgnInRequestEntity: SIgnInRequestEntity): AnswerServerSignIn {
        Log.d("serverTest",sIgnInRequestEntity.toString())
        Log.d("serverTest","email:\t${sIgnInRequestEntity.email}\ncode:\t${sIgnInRequestEntity.code}")
        val response = api.signIn(sIgnInRequestEntity.email,sIgnInRequestEntity.code)
        Log.d("serverTest","class UserNetworkImplementation:\n${response}")
        return response
    }


}