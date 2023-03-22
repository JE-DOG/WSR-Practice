package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import kotlinx.coroutines.launch

class AnalyzesViewModel(
    private val getAnalyzesCatalogUseCase: GetAnalyzesCatalogUseCase
):ViewModel() {

    private val analyzesLiveData= MutableLiveData<List<ResponseServerCatalog>>()
    val analyzes = analyzesLiveData

    private val categoryLiveData = MutableLiveData<String>()
    val category = categoryLiveData

    private val selectedAnalyzeLiveData = MutableLiveData<MutableList<ResponseServerCatalog>>()
    val _selectedAnalyzeLiveData = selectedAnalyzeLiveData



    fun addAnalyze(analyze: ResponseServerCatalog){
        Log.d("priceTest", analyze.toString())
        selectedAnalyzeLiveData.value?.add(analyze)
    }

    fun removeAnalyze(analyze:ResponseServerCatalog){
        selectedAnalyzeLiveData.value?.remove(analyze)
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

}