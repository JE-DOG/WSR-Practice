package com.example.wsrpractice.domain.use_case.user.patient_card

import com.example.wsrpractice.domain.repository.PatientCardRepository
import retrofit2.Response

class SetAvatarUseCase(private val patientCardRepository: PatientCardRepository) {

    suspend fun execute(image:String):Response<Unit>{
        val response = patientCardRepository.avatarCard(image = image)
        return response
    }

}