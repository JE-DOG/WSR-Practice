package com.example.wsrpractice.domain.use_case.user.patient_card

import android.util.Log
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.domain.repository.PatientCardRepository
import com.example.wsrpractice.presentetion.model.PatientCard

class UpdatePatientCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(userPatientCardNetwork: UserPatientCardNetwork): PatientCard {
        val response = patientCardRepository.updateCard(userPatientCardNetwork = userPatientCardNetwork)
        Log.d("ServerPatientTest",response.toString())
        return response.toPresentation()
    }

}