package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.domain.repository.PatientCardRepository
import com.example.wsrpractice.presentetion.model.PatientCard

class GetCardsUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute():List<PatientCard>{

        return patientCardRepository.getCards().map { it.toPresentation() }

    }

}