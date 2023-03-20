package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.domain.use_case.user.catalog.GetAnalyzesCatalogUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AnalyzesViewModel(
    private val getAnalyzesCatalogUseCase: GetAnalyzesCatalogUseCase
):ViewModel() {

    private val analyzesLiveData= MutableLiveData<List<ResponseServerCatalog>>()
    val analyzes = analyzesLiveData

    private val categoryLiveData = MutableLiveData<String>()
    val category = categoryLiveData

    private val fullPriceLiveData = MutableLiveData(0)
    val _fullPriceLiveData = fullPriceLiveData



    fun addPrice(textPrice:String){
        Log.d("priceTest",textPrice)
        val price = textPrice.toInt()
        fullPriceLiveData.value = fullPriceLiveData.value!!.plus(price)
    }

    fun removePrice(){
        fullPriceLiveData.value = 0
    }

    //fdsafsafdsdfdsaf

    fun getAnalyzesCategory(){
        viewModelScope.launch {
            val response = getAnalyzesCatalogUseCase.execute()
            Log.d("serverTestCatalog",response.toString())
            analyzesLiveData.value = response
        }
    }

    fun setCategory(category: String){
        categoryLiveData.value = category
    }

}