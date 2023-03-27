package com.example.wsrpractice.domain.use_case.user.analyze

import com.example.wsrpractice.domain.repository.AnalyzeRepository
import com.example.wsrpractice.presentetion.model.Analyze

class AddAnalyzesUseCase(private val repository: AnalyzeRepository) {

    suspend fun execute(analyze: Analyze) {
        repository.addAnalyze(analyze.toData())
    }

}