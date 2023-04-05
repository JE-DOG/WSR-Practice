package com.example.wsrpractice.presentetion.ui.screens.checkout_order.map

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentSetUserAddressBinding
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.CheckoutOrderViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

class FragmentSetUserAddress: BaseFragmentMvvm<FragmentSetUserAddressBinding, CheckoutOrderViewModel>(
    FragmentSetUserAddressBinding::inflate
) {

    override val viewModel: CheckoutOrderViewModel by viewModels()
    private var geoPoint = GeoPoint(42.0,47.0)

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

    private fun initMap(){
        Configuration.getInstance().load(requireContext(),requireActivity().getSharedPreferences("OSM", Context.MODE_PRIVATE))

        val map = binding.mapView.apply {
            this.setTileSource(TileSourceFactory.MAPNIK)
            this.setMultiTouchControls(true)
            this.minZoomLevel = 9.0
            this.maxZoomLevel = 20.0


            this.controller.apply {
                setZoom(7.0)
                setCenter(geoPoint)
            }

            invalidate()
        }



    }

    private fun initButs(){
        val getLocation = binding.getLocationBut.apply {
            setOnClickListener {

            }
        }
    }

}