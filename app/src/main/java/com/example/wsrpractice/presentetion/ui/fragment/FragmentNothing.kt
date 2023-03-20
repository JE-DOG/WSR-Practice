package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsrpractice.databinding.FragmentNothingScreenBinding

class FragmentNothing:Fragment() {

    lateinit var binding:FragmentNothingScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNothingScreenBinding.inflate(inflater)
        return binding.root
    }

}