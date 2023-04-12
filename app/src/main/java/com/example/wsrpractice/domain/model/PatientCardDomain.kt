package com.example.wsrpractice.domain.model

import com.example.wsrpractice.data.storage.model.PatientCardEntity
import com.example.wsrpractice.presentetion.model.PatientCard

data class PatientCardDomain(
    val id:Int?,
    val firstname:String,
    val middlename:String,
    val lastname:String,
    val bith:String,
    val pol:String,
    val image:String? = null
) {

    fun toStorage(): PatientCardEntity{
        return this.run {
            PatientCardEntity(
                id = id,
                firstname = firstname,
                middlename = middlename,
                lastname = lastname,
                bith = bith,
                pol = pol
            )
        }
    }

    fun toPresentation():PatientCard{

        return this.run {
            PatientCard(
                firstname = firstname,
                middlename = middlename,
                lastname = lastname,
                bith = bith,
                pol = pol
            )
        }

    }

}