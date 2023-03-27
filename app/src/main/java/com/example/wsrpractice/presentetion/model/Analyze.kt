package com.example.wsrpractice.presentetion.model

import com.example.wsrpractice.data.storage.model.AnalyzeEntity
import com.example.wsrpractice.domain.model.AnalyzeDomain

data class Analyze(
    val name:String,
    val price:String,
    var patients:Int
){
    fun toDomain(): AnalyzeDomain{
        return this.run{
            AnalyzeDomain(
                id = 0,
                name = name,
                price = price,
                patients = patients
            )
        }
    }

    fun toData(): AnalyzeEntity{
        return this.run{
            AnalyzeEntity(
                id = 0,
                name = name,
                price = price,
                patients = patients
            )
        }
    }
}
