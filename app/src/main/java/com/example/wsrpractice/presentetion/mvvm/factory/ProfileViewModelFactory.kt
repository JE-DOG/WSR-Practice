package com.example.wsrpractice.presentetion.mvvm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wsrpractice.data.network.impl.pateint_card.PatientCardNetworkImpl
import com.example.wsrpractice.data.repository.PatientCardRepositoryImpl
import com.example.wsrpractice.data.storage.impl.patient_card.PatientCardStorageImpl
import com.example.wsrpractice.domain.use_case.user.patient_card.CreatePatientCardUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.SetAvatarUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.UpdatePatientCardUseCase
import com.example.wsrpractice.presentetion.mvvm.ProfileViewModel

class ProfileViewModelFactory:ViewModelProvider.Factory {

    private val patientCardNetwork by lazy {
        PatientCardNetworkImpl()
    }

    private val patientCardStorage by lazy {
        PatientCardStorageImpl()
    }

    private val patientCardRepository by lazy {
        PatientCardRepositoryImpl(
            patientCardNetwork = patientCardNetwork,
            patientCardStorage = patientCardStorage
        )
    }

    private val createPatientCardUseCase by lazy {
        CreatePatientCardUseCase(patientCardRepository = patientCardRepository)
    }

    private val updatePatientCardUseCase by lazy {
        UpdatePatientCardUseCase(patientCardRepository = patientCardRepository)
    }

    private val setAvatarUseCase by lazy {
        SetAvatarUseCase(patientCardRepository = patientCardRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            createPatientCardUseCase = createPatientCardUseCase,
            updatePatientCardUseCase = updatePatientCardUseCase,
            avatarUseCase = setAvatarUseCase) as T
    }

}