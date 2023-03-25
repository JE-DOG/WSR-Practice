package com.example.wsrpractice.data.network.impl.pateint_card

import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import retrofit2.Response

interface PatientCardNetwork {

    suspend fun createCard(token:String, userPatientCardNetwork: UserPatientCardNetwork): ResponseCreatePatientCard

    suspend fun updateCard(token:String, userPatientCardNetwork: UserPatientCardNetwork): ResponseUpdatePatientCard

    suspend fun avatarCard(token:String,image:String):Response<Unit>

}