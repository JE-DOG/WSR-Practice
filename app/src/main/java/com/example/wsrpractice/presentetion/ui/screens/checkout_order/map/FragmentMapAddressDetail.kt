package com.example.wsrpractice.presentetion.ui.screens.checkout_order.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentMapAddressDetailBinding
import com.example.wsrpractice.presentetion.ui.screens.Screens
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.CheckoutOrderViewModel

class FragmentMapAddressDetail: BaseFragmentMvvm<FragmentMapAddressDetailBinding, CheckoutOrderViewModel>(
    FragmentMapAddressDetailBinding::inflate
) {


    override val viewModel: CheckoutOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initButs()

    }

    private fun initButs(){


    }

}