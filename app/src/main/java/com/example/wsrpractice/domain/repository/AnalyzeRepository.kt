package com.example.wsrpractice.domain.repository

import com.example.wsrpractice.data.storage.model.AnalyzeEntity

interface AnalyzeRepository {

    suspend fun addAnalyze(analyzeEntity: AnalyzeEntity)

    suspend fun setPatients(name:String, patients:Int)

    suspend fun removeAnalyze(analyzeEntity: AnalyzeEntity)

    suspend fun getAnalyzes(): List<AnalyzeEntity>

    suspend fun removeAllAnalyzes()

}