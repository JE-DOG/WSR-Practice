package com.example.wsrpractice.data.repository

import android.util.Log
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.impl.pateint_card.PatientCardNetwork
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import com.example.wsrpractice.domain.repository.PatientCardRepository
import retrofit2.Response

class PatientCardRepositoryImpl(private val patientCardNetwork: PatientCardNetwork): PatientCardRepository {

    private val token = "Bearer ${App.token}"

    override suspend fun createCard(userPatientCard: UserPatientCard): ResponseCreatePatientCard {
        val response = patientCardNetwork.createCard(token = token, userPatientCard = userPatientCard)
        Log.d("ServerPatientTest",response.toString())

        return response
    }

    override suspend fun updateCard(userPatientCard: UserPatientCard): ResponseUpdatePatientCard {
        val response = patientCardNetwork.updateCard(token = token, userPatientCard = userPatientCard)
        Log.d("ServerPatientTest",response.toString())

        return response
    }

    override suspend fun avatarCard(image: String):Response<Unit> {
        val response = patientCardNetwork.avatarCard(token = token, image = image)
        Log.d("ServerPatientTest",response.toString())

        return response
    }


}