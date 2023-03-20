package com.example.wsrpractice.presentetion.ui.fragment.ficha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsrpractice.databinding.FragmentSecondFichaBinding

class FragmentSecondFicha: Fragment() {

    lateinit var binding:FragmentSecondFichaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondFichaBinding.inflate(inflater)
        return binding.root
    }

}