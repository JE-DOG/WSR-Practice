package com.example.wsrpractice.data.network.api

import com.example.wsrpractice.data.network.Network
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.AnswerServerSignIn
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity
import kotlinx.coroutines.runBlocking
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.Scanner

private const val SEND_CODE = "/api/sendCode"
private const val SIGN_IN = "/api/signin"

interface UserApi{

    @POST(SEND_CODE)
    suspend fun sendCode(@Header("email") email:String): AnswerServerSendCode

    @POST(SIGN_IN)
    suspend fun signIn(@Header("email") email: String,@Header("code") code:String): AnswerServerSignIn

}