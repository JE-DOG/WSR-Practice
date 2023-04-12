package com.example.wsrpractice.data.repository

import android.util.Log
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.impl.pateint_card.PatientCardNetwork
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.data.storage.impl.patient_card.PatientCardStorage
import com.example.wsrpractice.domain.model.PatientCardDomain
import com.example.wsrpractice.domain.repository.PatientCardRepository
import retrofit2.Response

class PatientCardRepositoryImpl(
    private val patientCardNetwork: PatientCardNetwork,
    private val patientCardStorage: PatientCardStorage
    ): PatientCardRepository {

    private val token = "Bearer ${App.INSTANCE.token}"

    override suspend fun createCard(userPatientCardNetwork: UserPatientCardNetwork): PatientCardDomain {
        val response = patientCardNetwork.createCard(token = token, userPatientCardNetwork = userPatientCardNetwork)
        Log.d("ServerPatientTest",response.toString())

        return response.toDomain()
    }

    override suspend fun updateCard(userPatientCardNetwork: UserPatientCardNetwork): PatientCardDomain {
        val response = patientCardNetwork.updateCard(token = token, userPatientCardNetwork = userPatientCardNetwork)
        Log.d("ServerPatientTest",response.toString())

        return response.toDomain()
    }

    override suspend fun avatarCard(image: String):Response<Unit> {
        val response = patientCardNetwork.avatarCard(token = token, image = image)
        Log.d("ServerPatientTest",response.toString())

        return response
    }

    override suspend fun getCards(): List<PatientCardDomain> {
        return patientCardStorage.getCards().map { it.toDomain() }
    }

    override suspend fun addCard(patientCardDomain: PatientCardDomain) {
        patientCardStorage.addCard(patientCardDomain.toStorage())
    }

    override suspend fun removeCard(patientCardDomain: PatientCardDomain) {
        patientCardStorage.removeCard(patientCardDomain.toStorage())
    }
}