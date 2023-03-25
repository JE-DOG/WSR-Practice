package com.example.wsrpractice.domain.repository

import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.domain.model.PatientCardDomain
import retrofit2.Response

interface PatientCardRepository {
    suspend fun createCard(userPatientCardNetwork: UserPatientCardNetwork): PatientCardDomain
    suspend fun updateCard(userPatientCardNetwork: UserPatientCardNetwork): PatientCardDomain
    suspend fun avatarCard(image: String):Response<Unit>


    suspend fun getCards(): List<PatientCardDomain>

    suspend fun addCard(patientCardDomain: PatientCardDomain)

    suspend fun removeCard(patientCardDomain: PatientCardDomain)

}