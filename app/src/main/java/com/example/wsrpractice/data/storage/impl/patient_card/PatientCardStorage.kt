package com.example.wsrpractice.data.storage.impl.patient_card

import com.example.wsrpractice.data.storage.model.PatientCardEntity

interface PatientCardStorage {

    suspend fun addCard(patientCardEntity: PatientCardEntity)

    suspend fun removeCard(patientCardEntity: PatientCardEntity)

    suspend fun getCards():List<PatientCardEntity>

}