package com.example.wsrpractice.domain.use_case.user.analyze

import androidx.core.text.buildSpannedString
import com.example.wsrpractice.domain.repository.AnalyzeRepository

class SetPatientsUseCase(private val analyzeRepository: AnalyzeRepository) {

    suspend fun execute(name:String, patients: Int){
        analyzeRepository.setPatients(
            name = name,
            patients = patients
        )
    }

}