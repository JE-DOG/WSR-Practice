package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.domain.repository.PatientCardRepository
import com.example.wsrpractice.presentetion.model.PatientCard

class CreatePatientCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(userPatientCardNetwork: UserPatientCardNetwork): PatientCard {
        val response = patientCardRepository.createCard(userPatientCardNetwork = userPatientCardNetwork)
        return response.toPresentation()
    }

}