package com.example.wsrpractice.data.network.api

import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import retrofit2.http.GET

private const val GET_CATALOG = "/api/catalog"

interface CatalogApi {

    @GET(GET_CATALOG)
    suspend fun getCatalog():List<ResponseServerCatalog>

}