package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsrpractice.databinding.FragmentBasketBinding

class FragmentBasket : Fragment() {

    lateinit var binding:FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
    }



}