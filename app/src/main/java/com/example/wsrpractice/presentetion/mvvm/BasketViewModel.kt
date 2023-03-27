package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.domain.use_case.user.analyze.GetAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.RemoveAnalyzeUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.SetPatientsUseCase
import com.example.wsrpractice.presentetion.model.Analyze
import kotlinx.coroutines.launch

class BasketViewModel(
    private val getAnalyzesUseCase: GetAnalyzesUseCase,
    private val setPatientsUseCase: SetPatientsUseCase,
    private val removeAnalyzeUseCase: RemoveAnalyzeUseCase
) : ViewModel() {

    private val analyzesLiveData = MutableLiveData<List<Analyze>>()
    val _analyzesLiveData = analyzesLiveData

    fun getAnalyzes(){
        viewModelScope.launch {
            val analyzes = getAnalyzesUseCase.execute()
            analyzesLiveData.postValue(analyzes)
        }
    }

    fun removeAnalyze(analyze: Analyze){
        viewModelScope.launch {
            removeAnalyzeUseCase.execute(analyze)
        }
    }

    fun setPatients(analyze: Analyze, add:Boolean){
        val index = analyzesLiveData.value?.indexOf(analyze)
        val patients = index?.let { analyzesLiveData.value?.get(it) }!!.patients

        if (add){
            index.let { analyzesLiveData.value?.get(it) }!!.patients++
        }else if( patients != 0 ){
            index.let { analyzesLiveData.value?.get(it) }!!.patients--
        }
    }

}