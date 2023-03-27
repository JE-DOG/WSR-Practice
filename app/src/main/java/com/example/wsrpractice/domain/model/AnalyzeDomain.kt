package com.example.wsrpractice.domain.model

import com.example.wsrpractice.data.storage.model.AnalyzeEntity
import com.example.wsrpractice.presentetion.model.Analyze


data class AnalyzeDomain(
    val id:Int,
    val name:String,
    val price:String,
    var patients:Int
){

    fun toData(): AnalyzeEntity{
        return this.run{
            AnalyzeEntity(
                id = id,
                name = name,
                price = price,
                patients = patients
            )
        }
    }

    fun toPresentation(): Analyze{
        return this.run{
            Analyze(
                name = name,
                price = price,
                patients = patients
            )
        }
    }

}
