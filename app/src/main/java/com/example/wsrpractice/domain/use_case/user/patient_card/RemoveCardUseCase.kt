package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.domain.repository.PatientCardRepository
import com.example.wsrpractice.presentetion.model.PatientCard

class RemoveCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(patientCard: PatientCard){
        patientCardRepository.removeCard(patientCard.toDomain())
    }

}