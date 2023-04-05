package com.example.wsrpractice.presentetion.ui.screens.checkout_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.osmdroid.util.GeoPoint

class CheckoutOrderViewModel: ViewModel() {

    private val geoPointLiveData = MutableLiveData<GeoPoint>()
    val _geoPointLiveData = geoPointLiveData

    fun setGeoPoint(newGeoPoint: GeoPoint){
        geoPointLiveData.value = newGeoPoint
    }

}