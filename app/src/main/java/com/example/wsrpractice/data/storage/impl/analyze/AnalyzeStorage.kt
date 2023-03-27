package com.example.wsrpractice.data.storage.impl.analyze

import com.example.wsrpractice.data.storage.model.AnalyzeEntity

interface AnalyzeStorage {

    suspend fun addAnalyze(analyzeEntity: AnalyzeEntity)

    suspend fun setPatients(name:String, patients:Int)

    suspend fun removeAnalyze(analyzeEntity: AnalyzeEntity)

    suspend fun getAnalyzes(): List<AnalyzeEntity>

}