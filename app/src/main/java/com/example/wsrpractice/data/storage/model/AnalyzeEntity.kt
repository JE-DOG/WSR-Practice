package com.example.wsrpractice.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wsrpractice.domain.model.AnalyzeDomain
import com.example.wsrpractice.presentetion.model.Analyze

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

    fun toPresentation(): Analyze {
        return this.run{
            Analyze(
                name = name,
                price = price,
                patients = patients
            )
        }
    }

}