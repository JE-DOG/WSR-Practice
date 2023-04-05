package com.example.wsrpractice.presentetion.ui.screens.checkout_order.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentSetUserAddressBinding
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.CheckoutOrderViewModel
import com.google.android.gms.location.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import com.example.wsrpractice.R
import org.osmdroid.events.MapEvent
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.views.overlay.MapEventsOverlay

private const val LOCATION_CODE = 1
class FragmentSetUserAddress: BaseFragmentMvvm<FragmentSetUserAddressBinding, CheckoutOrderViewModel>(
    FragmentSetUserAddressBinding::inflate
) {

    override val viewModel: CheckoutOrderViewModel by viewModels()
    lateinit var marker:Marker

    private val fusedLocationProvider by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initMap()

        initBottomSheet()
        initButs()


    }

    private fun initBottomSheet(){
        val bottomSheet = binding.container
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 200
            state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun setMarker(geoPoint: GeoPoint){
        val map = binding.mapView
        marker.position = geoPoint

        map.apply {
            controller.animateTo(geoPoint)
            overlays.add(marker)

            invalidate()
        }


    }

    private fun initMap(){
        Configuration.getInstance().load(requireContext(),requireActivity().getSharedPreferences("OSM", Context.MODE_PRIVATE))

        val startPoint = GeoPoint(42.0,49.0)

        val listener = object : MapEventsReceiver{

            override fun singleTapConfirmedHelper(touchGeo: GeoPoint?): Boolean {
                viewModel.setGeoPoint(touchGeo!!)

                return true
            }

            override fun longPressHelper(p: GeoPoint?): Boolean {

                return true
            }


        }

        val overlayListener = MapEventsOverlay(listener)

        val map = binding.mapView.apply {
            this.setTileSource(TileSourceFactory.MAPNIK)
            this.setMultiTouchControls(true)
            this.minZoomLevel = 9.0
            this.maxZoomLevel = 20.0
            this.overlays.add(overlayListener)


            this.controller.apply {
                setZoom(7.0)
                setCenter(startPoint)
            }

            invalidate()
        }

        marker = Marker(map).apply {
            this.icon = ActivityCompat.getDrawable(requireContext(),R.drawable.ic_my_location_marker)
            this.title = "Вы здесь"
        }

        viewModel._geoPointLiveData.observe(viewLifecycleOwner){
            setMarker(it)
        }

    }

    private fun initButs(){
        //get location button
        binding.getLocationBut.apply {
            setOnClickListener {
                initLocationSettings()
            }
        }
    }

    private fun initLocationSettings(){

        if (
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            ==
            PackageManager.PERMISSION_GRANTED
        ){

            val resultInterval:Long = 10

            val locationCallback = object :LocationCallback(){

            }

            fusedLocationProvider.requestLocationUpdates(
                LocationRequest.Builder(resultInterval * 1000).apply {
                    setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                }.build(),
                locationCallback,
                Looper.getMainLooper()
            )

            //получение данных о геолокации при успешном их получении
            fusedLocationProvider.lastLocation.addOnSuccessListener {

                viewModel.setGeoPoint(GeoPoint(it.latitude,it.longitude))

            }

        }else{

            Log.d("gpsTest","with problem get your position")


            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                LOCATION_CODE
            )

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_CODE){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initLocationSettings()
            }else{
                Toast.makeText(requireContext(),"Turn on gps", Toast.LENGTH_SHORT).show()
            }

        }
    }

}