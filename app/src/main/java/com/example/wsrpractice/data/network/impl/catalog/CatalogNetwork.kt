package com.example.wsrpractice.data.network.impl.catalog

import com.example.wsrpractice.data.network.model.ResponseServerCatalog

interface CatalogNetwork {

    suspend fun getCatalogs():List<ResponseServerCatalog>

}