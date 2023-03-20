package com.example.wsrpractice.domain.repository

import com.example.wsrpractice.data.network.impl.pateint_card.PatientCardNetwork
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import retrofit2.Response

interface PatientCardRepository {

    suspend fun createCard(userPatientCard: UserPatientCard): ResponseCreatePatientCard

    suspend fun updateCard(userPatientCard: UserPatientCard): ResponseUpdatePatientCard

    suspend fun avatarCard(image: String):Response<Unit>

}