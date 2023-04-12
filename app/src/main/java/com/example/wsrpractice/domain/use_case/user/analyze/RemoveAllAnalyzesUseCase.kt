package com.example.wsrpractice.domain.use_case.user.analyze

import com.example.wsrpractice.domain.repository.AnalyzeRepository

class RemoveAllAnalyzesUseCase(private val analyzeRepository: AnalyzeRepository) {

    suspend fun execute(){
        analyzeRepository.removeAllAnalyzes()
    }

}