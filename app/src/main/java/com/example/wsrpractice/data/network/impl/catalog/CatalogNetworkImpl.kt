package com.example.wsrpractice.data.network.impl.catalog

import com.example.wsrpractice.data.network.Network
import com.example.wsrpractice.data.network.impl.catalog.CatalogNetwork
import com.example.wsrpractice.data.network.model.ResponseServerCatalog

class CatalogNetworkImpl: CatalogNetwork {

    private val api = Network.apiCatalog

    override suspend fun getCatalogs(): List<ResponseServerCatalog> {
        val response = api.getCatalog()
        return response
    }

}