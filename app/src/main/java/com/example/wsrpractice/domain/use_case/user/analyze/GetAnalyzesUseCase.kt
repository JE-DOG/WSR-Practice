package com.example.wsrpractice.domain.use_case.user.analyze

import com.example.wsrpractice.domain.repository.AnalyzeRepository
import com.example.wsrpractice.presentetion.model.Analyze

class GetAnalyzesUseCase(private val analyzeRepository: AnalyzeRepository) {

    suspend fun execute(): List<Analyze> {
        return analyzeRepository.getAnalyzes().map { it.toPresentation() }
    }

}