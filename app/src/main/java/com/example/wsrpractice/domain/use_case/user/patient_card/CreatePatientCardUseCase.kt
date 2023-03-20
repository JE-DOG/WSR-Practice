package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import com.example.wsrpractice.domain.repository.PatientCardRepository
import retrofit2.Response

class CreatePatientCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(userPatientCard: UserPatientCard): ResponseCreatePatientCard {
        val response = patientCardRepository.createCard(userPatientCard = userPatientCard)
        return response
    }

}