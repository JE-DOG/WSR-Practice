package com.example.wsrpractice.domain.use_case.user.analyze

import com.example.wsrpractice.domain.repository.AnalyzeRepository
import com.example.wsrpractice.presentetion.model.Analyze

class RemoveAnalyzeUseCase(private val analyzeRepository: AnalyzeRepository) {

    suspend fun execute(analyze: Analyze){
        analyzeRepository.removeAnalyze(analyze.toData())
    }

}