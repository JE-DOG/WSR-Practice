package com.example.wsrpractice.presentetion.ui.screens.checkout_order.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentCheckoutOrderBinding
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.CheckoutOrderViewModel

class FragmentCheckoutOrder(): BaseFragmentMvvm<FragmentCheckoutOrderBinding, CheckoutOrderViewModel>(
    FragmentCheckoutOrderBinding::inflate
) {

    override val viewModel: CheckoutOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButs()

    }

    private fun initButs(){
        val address = binding.addressBut
        address.setOnClickListener {

        }


    }

}