package com.example.wsrpractice.data.repository

import com.example.wsrpractice.data.storage.impl.analyze.AnalyzeStorage
import com.example.wsrpractice.data.storage.model.AnalyzeEntity
import com.example.wsrpractice.domain.repository.AnalyzeRepository

class AnalyzeRepositoryImpl(private val storage: AnalyzeStorage): AnalyzeRepository {

    override suspend fun addAnalyze(analyzeEntity: AnalyzeEntity) {
        storage.addAnalyze(analyzeEntity)
    }

    override suspend fun setPatients(name: String, patients: Int) {
        storage.setPatients(
            name = name,
            patients = patients
        )
    }

    override suspend fun removeAnalyze(analyzeEntity: AnalyzeEntity) {
        storage.removeAnalyze(analyzeEntity)
    }

    override suspend fun getAnalyzes(): List<AnalyzeEntity> {
        return storage.getAnalyzes()
    }


}