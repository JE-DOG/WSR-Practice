package com.example.wsrpractice.data.network.api

import com.example.wsrpractice.data.network.model.ResponseCreatePatientCard
import com.example.wsrpractice.data.network.model.ResponseUpdatePatientCard
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

private const val  CREATE_CARD_API = "/api/createProfile"
private const val  UPDATE_CARD_API = "/api/updateProfile"
private const val  AVATAR_API = "/api/avatar"


interface PatientCardApi {

    @POST(CREATE_CARD_API)
    suspend fun createPatientCard(@Header("Authorization") token:String,@Body userPatientCardNetwork: UserPatientCardNetwork):ResponseCreatePatientCard

    @PUT(UPDATE_CARD_API)
    suspend fun updatePatientCard(@Header("Authorization") token:String,@Body userPatientCardNetwork: UserPatientCardNetwork):ResponseUpdatePatientCard

    @POST(AVATAR_API)
    suspend fun avatar(@Header("Authorization") token:String,@Header("binary") image:String):Response<Unit>

}