package com.example.wsrpractice.presentetion.screens.checkout_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentCheckoutOrderBinding

class FragmentCheckoutOrder(): BaseFragmentMvvm<FragmentCheckoutOrderBinding,CheckoutOrderViewModel>(
    FragmentCheckoutOrderBinding::inflate
) {

    override val viewModel: CheckoutOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}