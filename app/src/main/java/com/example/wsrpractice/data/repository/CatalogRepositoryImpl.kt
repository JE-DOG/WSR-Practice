package com.example.wsrpractice.data.repository

import com.example.wsrpractice.data.network.impl.catalog.CatalogNetwork
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.domain.repository.CatalogRepository

class CatalogRepositoryImpl(private val catalogNetwork: CatalogNetwork):CatalogRepository {

    override suspend fun getCatalogs():List<ResponseServerCatalog>{
        val response = catalogNetwork.getCatalogs()
        return response
    }

}