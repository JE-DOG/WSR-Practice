package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.domain.repository.PatientCardRepository

class CreatePatientCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(userPatientCardNetwork: UserPatientCardNetwork): ResponseCreatePatientCard {
        val response = patientCardRepository.createCard(userPatientCardNetwork = userPatientCardNetwork)
        return response
    }

}