package com.example.wsrpractice.presentetion.mvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wsrpractice.data.network.impl.catalog.CatalogNetworkImpl
import com.example.wsrpractice.data.repository.CatalogRepositoryImpl
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import com.example.wsrpractice.presentetion.mvvm.AnalyzesViewModel

class AnalyzesViewModelFactory: ViewModelProvider.Factory {

    private val catalogNetwork by lazy {
        CatalogNetworkImpl()
    }

    private val catalogRepository by lazy {
        CatalogRepositoryImpl(catalogNetwork)
    }

    private val getAnalyzesCatalogUseCase by lazy{
        GetAnalyzesCatalogUseCase(catalogRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AnalyzesViewModel(
            getAnalyzesCatalogUseCase = getAnalyzesCatalogUseCase
        ) as T
    }


}