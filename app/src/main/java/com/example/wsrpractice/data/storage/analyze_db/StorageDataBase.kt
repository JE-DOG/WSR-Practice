package com.example.wsrpractice.data.storage.analyze_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wsrpractice.data.storage.dao.AnalyzeDao
import com.example.wsrpractice.data.storage.model.AnalyzeEntity

@Database(entities = [AnalyzeEntity::class], version = 1)
abstract class StorageDataBase:RoomDatabase() {

    abstract fun analyzeDao():AnalyzeDao

}