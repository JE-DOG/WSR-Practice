package com.example.wsrpractice.data.storage.dao

import androidx.room.*
import com.example.wsrpractice.data.storage.model.AnalyzeEntity
import com.example.wsrpractice.data.storage.model.PatientCardEntity

@Dao
interface PatientCardDao {

    @Insert(PatientCardEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(patientCardEntity: PatientCardEntity):Long

    @Delete(PatientCardEntity::class)
    suspend fun removeCard(patientCardEntity: PatientCardEntity):Long

    @Query("SELECT * " +
                "FROM patient_card_table")
    suspend fun getCards():List<PatientCardEntity>

}