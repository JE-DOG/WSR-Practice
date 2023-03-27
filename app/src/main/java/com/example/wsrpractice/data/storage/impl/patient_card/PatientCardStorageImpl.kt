package com.example.wsrpractice.data.storage.impl.patient_card

import com.example.wsrpractice.App
import com.example.wsrpractice.data.storage.model.PatientCardEntity

class PatientCardStorageImpl:PatientCardStorage {

    private val db = App.INSTANCE.roomDataBase.patientCardDao()

    override suspend fun addCard(patientCardEntity: PatientCardEntity) {
        db.addCard(patientCardEntity)
    }

    override suspend fun removeCard(patientCardEntity: PatientCardEntity) {
        db.removeCard(patientCardEntity)
    }

    override suspend fun getCards(): List<PatientCardEntity> {
        return db.getCards()
    }

}
