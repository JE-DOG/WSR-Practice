package com.example.wsrpractice.data.storage.impl.analyze

import com.example.wsrpractice.App
import com.example.wsrpractice.data.storage.model.AnalyzeEntity

class AnalyzeStorageImpl: AnalyzeStorage {

    private val db = App.INSTANCE.roomDataBase.analyzeDao()

    override suspend fun addAnalyze(analyzeEntity: AnalyzeEntity) {
        db.addAnalyze(analyzeEntity)
    }

    override suspend fun setPatients(name: String, patients: Int) {
        db.setPatients(
            name = name,
            patients = patients
        )
    }

    override suspend fun removeAnalyze(analyzeEntity: AnalyzeEntity) {
        db.removeAnalyze(analyzeEntity)
    }

    override suspend fun getAnalyzes(): List<AnalyzeEntity> {
        return db.getAnalyzes()
    }

    override suspend fun removeAllAnalyze() {
        db.removeAllAnalyzes()
    }


}