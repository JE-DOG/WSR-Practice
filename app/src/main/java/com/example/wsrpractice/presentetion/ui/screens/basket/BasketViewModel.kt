package com.example.wsrpractice.presentetion.ui.screens.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.domain.use_case.user.analyze.GetAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.RemoveAllAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.RemoveAnalyzeUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.SetPatientsUseCase
import com.example.wsrpractice.presentetion.model.Analyze
import kotlinx.coroutines.launch

class BasketViewModel(
    private val getAnalyzesUseCase: GetAnalyzesUseCase,
    private val setPatientsUseCase: SetPatientsUseCase,
    private val removeAnalyzeUseCase: RemoveAnalyzeUseCase,
    private val removeAllAnalyzesUseCase: RemoveAllAnalyzesUseCase
) : ViewModel() {

    private val analyzesLiveData = MutableLiveData<MutableList<Analyze>>()
    val _analyzesLiveData = analyzesLiveData

    fun getPrice():String{
        var price = 0
        analyzesLiveData.value?.forEach {
            price += it.price.toInt()
        }
        return price.toString()
    }
    fun removeAllAnalyze(){
        viewModelScope.launch {
            removeAllAnalyzesUseCase.execute()
            analyzesLiveData.value = mutableListOf()
        }
    }

    fun getAnalyzes(){
        viewModelScope.launch {
            val analyzes = getAnalyzesUseCase.execute()
            analyzesLiveData.postValue(analyzes.toMutableList())
        }
    }

    fun removeAnalyze(analyze: Analyze){
        viewModelScope.launch {
            removeAnalyzeUseCase.execute(analyze)
            analyzesLiveData.value?.remove(analyze)
            analyzesLiveData.value = analyzesLiveData.value
        }
    }

    fun setPatients(analyze: Analyze, isAdd:Boolean){
        val index = analyzesLiveData.value?.indexOf(analyze)
        val patients = index?.let { analyzesLiveData.value?.get(it) }!!.patients
        Log.d("RcvListenerTest",patients.toString())

        if (isAdd){
            index.let { analyzesLiveData.value?.get(it) }!!.patients++
        }else if( patients != 1 ){
            index.let { analyzesLiveData.value?.get(it) }!!.patients--
        }
        Log.d("RcvListenerTest",index.let { analyzesLiveData.value?.get(it) }!!.patients.toString())

    }

}