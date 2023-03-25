package com.example.wsrpractice.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wsrpractice.domain.model.AnalyzeDomain

@Entity(tableName = "analyze_table")
data class AnalyzeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val price:String,
    var patients:Int
){

    fun toDomain(): AnalyzeDomain{
        return this.run{
            AnalyzeDomain(
                id = id,
                name = name,
                price = price,
                patients = patients
            )
        }
    }

}