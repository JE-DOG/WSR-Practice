package com.example.wsrpractice.presentetion.mvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.impl.catalog.CatalogNetworkImpl
import com.example.wsrpractice.data.repository.AnalyzeRepositoryImpl
import com.example.wsrpractice.data.repository.CatalogRepositoryImpl
import com.example.wsrpractice.data.storage.impl.analyze.AnalyzeStorageImpl
import com.example.wsrpractice.domain.use_case.user.analyze.AddAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.GetAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import com.example.wsrpractice.presentetion.mvvm.AnalyzesViewModel

class AnalyzesViewModelFactory: ViewModelProvider.Factory {

    //catalog

    private val catalogNetwork by lazy {
        CatalogNetworkImpl()
    }

    private val catalogRepository by lazy {
        CatalogRepositoryImpl(catalogNetwork)
    }

    private val getAnalyzesCatalogUseCase by lazy{
        GetAnalyzesCatalogUseCase(catalogRepository)
    }

    //analyzes

    private val analyzeStorage by lazy {
        AnalyzeStorageImpl()
    }

    private val analyzeRepository by lazy {
        AnalyzeRepositoryImpl(analyzeStorage)
    }



    private val addAnalyzesCatalogUseCase by lazy {
        AddAnalyzesUseCase(analyzeRepository)
    }

    private val getAnalyzesUseCase by lazy {
        GetAnalyzesUseCase(analyzeRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when(modelClass){
            AnalyzesViewModel::class.java -> {
                AnalyzesViewModel(
                    getAnalyzesUseCase,
                    getAnalyzesCatalogUseCase,
                    addAnalyzesCatalogUseCase
                ) as T
            }
            else -> {
                throw java.lang.Exception()
            }
        }
    }


}