package com.example.wsrpractice.data.network.impl.pateint_card

import android.annotation.SuppressLint
import android.util.Log
import com.example.wsrpractice.data.network.Network
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class PatientCardNetworkImpl: PatientCardNetwork {

    private val api = Network.apiPatientCard

    override suspend fun createCard(token:String,userPatientCard: UserPatientCard): ResponseCreatePatientCard {
        Log.d("ServerPatientTest", Gson().toJson(userPatientCard))

        val response = api.createPatientCard(
            token = token,
            userPatientCard = userPatientCard
        )
        Log.d("ServerPatientTest", response.toString())
        return response

    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun updateCard(token:String, userPatientCard: UserPatientCard): ResponseUpdatePatientCard {
        Log.d("ServerPatientTest", Gson().toJson(userPatientCard))


        val response = api.updatePatientCard(token = token, userPatientCard = userPatientCard)
        Log.d("ServerPatientTest",response.toString())
        return response

    }

    override suspend fun avatarCard(token:String,image: String):Response<Unit> {
        Log.d("ServerPatientTest", Gson().toJson(image))

        val response = api.avatar(token = token, image = image)
        Log.d("ServerPatientTest",response.toString())

        return response
    }

}