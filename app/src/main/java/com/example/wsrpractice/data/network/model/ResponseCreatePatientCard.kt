package com.example.wsrpractice.data.network.model

import com.example.wsrpractice.domain.model.PatientCardDomain

data class ResponseCreatePatientCard(
    val bith: String,
    val firstname: String,
    val id: Int,
    val image: String,
    val lastname: String,
    val middlename: String,
    val pol: String
){
    fun toDomain(): PatientCardDomain {
        return this.run {
            PatientCardDomain(
                id = id,
                firstname = firstname,
                middlename = middlename,
                lastname = lastname,
                bith = bith,
                pol = pol
            )
        }
    }
}