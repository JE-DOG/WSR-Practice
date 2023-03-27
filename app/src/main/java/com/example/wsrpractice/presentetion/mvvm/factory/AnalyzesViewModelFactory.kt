package com.example.wsrpractice.presentetion.mvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.impl.catalog.CatalogNetworkImpl
import com.example.wsrpractice.data.repository.AnalyzeRepositoryImpl
import com.example.wsrpractice.data.repository.CatalogRepositoryImpl
import com.example.wsrpractice.data.storage.impl.analyze.AnalyzeStorageImpl
import com.example.wsrpractice.domain.use_case.user.analyze.*
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import com.example.wsrpractice.presentetion.mvvm.AnalyzesViewModel
import com.example.wsrpractice.presentetion.mvvm.BasketViewModel

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

    private val setPatientsUseCase by lazy {
        SetPatientsUseCase(analyzeRepository)
    }

    private val removeAnalyzeUseCase by lazy {
        RemoveAnalyzeUseCase(analyzeRepository)
    }

    private val removeAllAnalyzesUseCase by lazy {
        RemoveAllAnalyzesUseCase(analyzeRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when(modelClass){

            AnalyzesViewModel::class.java -> {
                AnalyzesViewModel(
                    removeAllAnalyzesUseCase,
                    getAnalyzesCatalogUseCase,
                    addAnalyzesCatalogUseCase,
                    removeAnalyzeUseCase
                ) as T
            }

            BasketViewModel::class.java -> {
                BasketViewModel(
                    getAnalyzesUseCase,
                    setPatientsUseCase,
                    removeAnalyzeUseCase
                ) as T
            }

            else -> {
                throw java.lang.Exception()
            }
        }
    }


}