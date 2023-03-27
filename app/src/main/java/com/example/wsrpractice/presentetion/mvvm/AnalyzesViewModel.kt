package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.domain.use_case.user.analyze.AddAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.analyze.GetAnalyzesUseCase
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import com.example.wsrpractice.presentetion.model.Analyze
import kotlinx.coroutines.launch

class AnalyzesViewModel(
    private val getAnalyzesUseCase: GetAnalyzesUseCase,
    private val getAnalyzesCatalogUseCase: GetAnalyzesCatalogUseCase,
    private val addAnalyzesUseCase: AddAnalyzesUseCase
):ViewModel() {

    private val analyzesLiveData= MutableLiveData<List<ResponseServerCatalog>>()
    val analyzes = analyzesLiveData

    private val categoryLiveData = MutableLiveData<String>()
    val category = categoryLiveData

    private val selectedAnalyzeLiveData = MutableLiveData<MutableList<ResponseServerCatalog>>(
        mutableListOf()
    )
    val _selectedAnalyzeLiveData = selectedAnalyzeLiveData

    fun addAnalyze(analyze: ResponseServerCatalog){
        Log.d("priceTest", analyze.toString())
        selectedAnalyzeLiveData.value!!.add(analyze)
        Log.d("listTest", selectedAnalyzeLiveData.toString())
        selectedAnalyzeLiveData.value = selectedAnalyzeLiveData.value
    }

    fun removeAnalyze(analyze:ResponseServerCatalog){
        selectedAnalyzeLiveData.value!!.remove(analyze)
        selectedAnalyzeLiveData.value =  selectedAnalyzeLiveData.value
    }

    fun getAnalyzesCategory(){
        viewModelScope.launch {
            val response = getAnalyzesCatalogUseCase.execute()
            Log.d("serverTestCatalog",response.toString())
            analyzesLiveData.postValue(response)
        }
    }

    fun setCategory(category: String){
        categoryLiveData.value = category
    }

    fun saveAnalyzes(){
        viewModelScope.launch {

            selectedAnalyzeLiveData.value!!.forEach {
                val analyze = Analyze(
                    it.name,
                    it.price,
                    1
                )
                addAnalyzesUseCase.execute(analyze = analyze)
            }
        }
    }

}

fun main(){

    val mutableLiveData = mutableListOf<Int>()
    println(mutableLiveData)

}