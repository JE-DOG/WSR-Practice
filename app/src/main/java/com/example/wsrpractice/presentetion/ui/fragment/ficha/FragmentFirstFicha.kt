package com.example.wsrpractice.presentetion.ui.fragment.ficha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsrpractice.databinding.FragmentFirstFichaBinding

class FragmentFirstFicha(private val layoutRes: Int): Fragment() {

    lateinit var binding:FragmentFirstFichaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes,container)

    }

}