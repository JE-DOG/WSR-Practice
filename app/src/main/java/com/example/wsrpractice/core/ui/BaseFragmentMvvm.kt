package com.example.wsrpractice.core.ui

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.wsrpractice.App

abstract class BaseFragmentMvvm<VB:ViewBinding,VM: ViewModel>(
    private val inflate: (inflater: LayoutInflater) -> VB
): BaseFragment<VB>(inflate) {


    abstract val viewModel: VM

}