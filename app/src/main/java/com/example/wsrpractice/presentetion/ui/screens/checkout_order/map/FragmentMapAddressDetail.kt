package com.example.wsrpractice.presentetion.ui.screens.checkout_order.map

import androidx.fragment.app.viewModels
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentMapAddressDetailBinding
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.CheckoutOrderViewModel

class FragmentMapAddressDetail: BaseFragmentMvvm<FragmentMapAddressDetailBinding, CheckoutOrderViewModel>(
    FragmentMapAddressDetailBinding::inflate
) {

    override val viewModel: CheckoutOrderViewModel by viewModels()



}