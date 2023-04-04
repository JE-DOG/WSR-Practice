package com.example.wsrpractice.presentetion.mvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wsrpractice.data.network.impl.catalog.CatalogNetworkImpl
import com.example.wsrpractice.data.network.impl.pateint_card.PatientCardNetworkImpl
import com.example.wsrpractice.data.repository.AnalyzeRepositoryImpl
import com.example.wsrpractice.data.repository.CatalogRepositoryImpl
import com.example.wsrpractice.data.repository.PatientCardRepositoryImpl
import com.example.wsrpractice.data.storage.impl.analyze.AnalyzeStorageImpl
import com.example.wsrpractice.data.storage.impl.patient_card.PatientCardStorageImpl
import com.example.wsrpractice.domain.use_case.user.analyze.*
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.CreatePatientCardUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.SetAvatarUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.UpdatePatientCardUseCase
import com.example.wsrpractice.presentetion.screens.analyze.AnalyzesViewModel
import com.example.wsrpractice.presentetion.screens.basket.BasketViewModel
import com.example.wsrpractice.presentetion.screens.profile.ProfileViewModel

class ViewModelsFactory: ViewModelProvider.Factory {

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
    //analyze repo
    private val analyzeRepository by lazy {
        AnalyzeRepositoryImpl(analyzeStorage)
    }


    //add analyze
    private val addAnalyzesCatalogUseCase by lazy {
        AddAnalyzesUseCase(analyzeRepository)
    }
    //get analyzes
    private val getAnalyzesUseCase by lazy {
        GetAnalyzesUseCase(analyzeRepository)
    }
    //set patients
    private val setPatientsUseCase by lazy {
        SetPatientsUseCase(analyzeRepository)
    }
    //remove analyze
    private val removeAnalyzeUseCase by lazy {
        RemoveAnalyzeUseCase(analyzeRepository)
    }
    //remove all analyzes
    private val removeAllAnalyzesUseCase by lazy {
        RemoveAllAnalyzesUseCase(analyzeRepository)
    }

    //patient network
    private val patientCardNetwork by lazy {
        PatientCardNetworkImpl()
    }
    //patient storage
    private val patientCardStorage by lazy {
        PatientCardStorageImpl()
    }
    //patient repo
    private val patientCardRepository by lazy {
        PatientCardRepositoryImpl(
            patientCardNetwork = patientCardNetwork,
            patientCardStorage = patientCardStorage
        )
    }
    // patient create card
    private val createPatientCardUseCase by lazy {
        CreatePatientCardUseCase(patientCardRepository = patientCardRepository)
    }
    // patient update card
    private val updatePatientCardUseCase by lazy {
        UpdatePatientCardUseCase(patientCardRepository = patientCardRepository)
    }
    // patient set avatar
    private val setAvatarUseCase by lazy {
        SetAvatarUseCase(patientCardRepository = patientCardRepository)
    }
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when(modelClass){


            //analyzed : 1.remove,2.get,3.add,4.remove
            AnalyzesViewModel::class.java -> {
                AnalyzesViewModel(
                    removeAllAnalyzesUseCase,
                    getAnalyzesCatalogUseCase,
                    addAnalyzesCatalogUseCase,
                    removeAnalyzeUseCase
                ) as T
            }
            //analyze: 1.get,2.remove,3.remove all
            //patient: 1.set
            BasketViewModel::class.java -> {
                BasketViewModel(
                    getAnalyzesUseCase,
                    setPatientsUseCase,
                    removeAnalyzeUseCase,
                    removeAllAnalyzesUseCase
                ) as T
            }

            ProfileViewModel::class.java -> {
                ProfileViewModel(
                    createPatientCardUseCase = createPatientCardUseCase,
                    updatePatientCardUseCase = updatePatientCardUseCase,
                    avatarUseCase = setAvatarUseCase
                ) as T
            }

            else -> {
                throw java.lang.Exception()
            }
        }
    }


}