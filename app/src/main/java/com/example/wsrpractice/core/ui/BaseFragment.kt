package com.example.wsrpractice.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.wsrpractice.App
import com.example.wsrpractice.presentetion.ui.screens.Screens
import com.github.terrakok.cicerone.Screen

abstract class BaseFragment<VB:ViewBinding>(
    private val inflate: (inflater: LayoutInflater) -> VB
): Fragment() {

    protected val router = App.INSTANCE.router
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        _binding = inflate.invoke(inflater)
        return binding.root
    }

    open fun init(){}

    open fun back(screen: Screen?){
        router.backTo(screen)
    }


}