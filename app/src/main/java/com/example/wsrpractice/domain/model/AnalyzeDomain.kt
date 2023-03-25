package com.example.wsrpractice.domain.model

import com.example.wsrpractice.data.storage.model.AnalyzeEntity


data class AnalyzeDomain(
    val id:Int,
    val name:String,
    val price:String,
    var patients:Int
){

    fun toDomain(): AnalyzeEntity{
        return this.run{
            AnalyzeEntity(
                id = id,
                name = name,
                price = price,
                patients = patients
            )
        }
    }

}
