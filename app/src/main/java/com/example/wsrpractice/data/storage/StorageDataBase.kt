package com.example.wsrpractice.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wsrpractice.data.storage.dao.AnalyzeDao
import com.example.wsrpractice.data.storage.dao.PatientCardDao
import com.example.wsrpractice.data.storage.model.AnalyzeEntity
import com.example.wsrpractice.data.storage.model.PatientCardEntity

@Database(entities = [AnalyzeEntity::class,PatientCardEntity::class], version = 2)
abstract class StorageDataBase:RoomDatabase() {

    abstract fun analyzeDao():AnalyzeDao

    abstract fun patientCardDao(): PatientCardDao

}