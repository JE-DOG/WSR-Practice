package com.example.wsrpractice.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.api.CatalogApi
import com.example.wsrpractice.data.network.api.PatientCardApi
import com.example.wsrpractice.data.network.api.UserApi
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL =  "https://medic.madskill.ru"

object Network {

    lateinit var apiUser: UserApi
    lateinit var apiCatalog: CatalogApi
    lateinit var apiPatientCard: PatientCardApi

    fun init(){

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiUser = retrofit.create(UserApi::class.java)

        apiCatalog = retrofit.create(CatalogApi::class.java)

        apiPatientCard = retrofit.create(PatientCardApi::class.java)
    }

    private fun checkConnection(context: Context): Boolean{
        val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInfo = networkManager.activeNetworkInfo
        Log.d("NetworkTest",(activeInfo != null && activeInfo.isConnected).toString())


        return activeInfo != null && activeInfo.isConnected
    }



}


