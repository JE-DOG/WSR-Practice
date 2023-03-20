package com.example.wsrpractice.presentetion.ui.fragment.ficha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsrpractice.databinding.FragmentThirdFichaBinding

class FragmentThirdFicha: Fragment() {

    lateinit var binding:FragmentThirdFichaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdFichaBinding.inflate(inflater)
        return binding.root
    }

}