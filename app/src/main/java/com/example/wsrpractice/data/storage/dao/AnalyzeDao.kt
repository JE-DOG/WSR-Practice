package com.example.wsrpractice.data.storage.dao

import androidx.room.*
import com.example.wsrpractice.data.storage.model.AnalyzeEntity

@Dao
interface AnalyzeDao {

    @Insert(AnalyzeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnalyze(analyzeEntity: AnalyzeEntity)

    @Query("UPDATE analyze_table " +
            "SET patients = :patients " +
            "WHERE name = :name")
    suspend fun setPatients(name:String, patients:Int)

    @Delete(AnalyzeEntity::class)
    suspend fun removeAnalyze(analyzeEntity: AnalyzeEntity)

    @Query("SELECT * " +
            "FROM analyze_table")
    suspend fun getAnalyzes(): List<AnalyzeEntity>

    @Query("DELETE FROM analyze_table ")
    suspend fun removeAllAnalyzes()

}