package com.example.wsrpractice.presentetion.model

import com.example.wsrpractice.domain.model.PatientCardDomain

data class PatientCard(
    val firstname:String,
    val middlename:String,
    val lastname:String,
    val bith:String,
    val pol:String,
    val image:String? = null
){

    fun toDomain(): PatientCardDomain{
        return this.run {
            PatientCardDomain(
                id = null,
                firstname = firstname,
                middlename = middlename,
                lastname = lastname,
                bith = bith,
                pol = pol
            )
        }
    }

}
