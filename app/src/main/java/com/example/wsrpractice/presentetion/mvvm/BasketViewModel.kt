package com.example.wsrpractice.presentetion.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.domain.use_case.user.analyze.GetAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.SetPatientsUseCase
import com.example.wsrpractice.presentetion.model.Analyze
import kotlinx.coroutines.launch

class BasketViewModel(
    private val getAnalyzesUseCase: GetAnalyzesUseCase,
    private val setPatientsUseCase: SetPatientsUseCase
) : ViewModel() {

    private val analyzesLiveData = MutableLiveData<List<Analyze>>()
    val _analyzesLiveData = analyzesLiveData

    fun getAnalyzes(){
        viewModelScope.launch {
            val analyzes = getAnalyzesUseCase.execute()
            analyzesLiveData.postValue(analyzes)
        }
    }

    fun setPatients(){

    }

}