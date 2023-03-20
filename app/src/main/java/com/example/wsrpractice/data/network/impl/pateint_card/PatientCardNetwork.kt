package com.example.wsrpractice.data.network.impl.pateint_card

import com.example.wsrpractice.data.network.model.AnswerServerSendCode
import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCard
import retrofit2.Response

interface PatientCardNetwork {

    suspend fun createCard(token:String,userPatientCard: UserPatientCard): ResponseCreatePatientCard

    suspend fun updateCard(token:String,userPatientCard: UserPatientCard): ResponseUpdatePatientCard

    suspend fun avatarCard(token:String,image:String):Response<Unit>

}