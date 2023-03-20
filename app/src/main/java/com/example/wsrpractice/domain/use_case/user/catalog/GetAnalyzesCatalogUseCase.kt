package com.example.wsrpractice.domain.use_case.user.catalog

import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.domain.repository.CatalogRepository

class GetAnalyzesCatalogUseCase(private val catalogRepository: CatalogRepository) {

    suspend fun execute():List<ResponseServerCatalog>{
        val response = catalogRepository.getCatalogs()
        return response
    }

}