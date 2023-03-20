package com.example.wsrpractice.domain.use_case.user.patient_card

import android.util.Log
import android.widget.Toast
import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import com.example.wsrpractice.domain.repository.PatientCardRepository
import retrofit2.Response

class UpdatePatientCardUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(userPatientCard: UserPatientCard): ResponseUpdatePatientCard{
        val response = patientCardRepository.updateCard(userPatientCard = userPatientCard)
        Log.d("ServerPatientTest",response.toString())
        return response
    }

}