package com.example.wsrpractice.domain.repository

import com.example.wsrpractice.data.network.model.ResponseServerCatalog

interface CatalogRepository {

    suspend fun getCatalogs():List<ResponseServerCatalog>

}