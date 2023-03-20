package com.example.wsrpractice.data.network

import com.example.wsrpractice.data.network.api.CatalogApi
import com.example.wsrpractice.data.network.api.PatientCardApi
import com.example.wsrpractice.data.network.api.UserApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =  "https://medic.madskill.ru"

object Network {

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiUser: UserApi = retrofit.create(UserApi::class.java)

    val apiCatalog: CatalogApi = retrofit.create(CatalogApi::class.java)

    val apiPatientCard: PatientCardApi = retrofit.create(PatientCardApi::class.java)

}


