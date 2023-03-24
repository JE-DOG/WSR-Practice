package com.example.wsrpractice.presentetion.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.databinding.FragmentAnalyzeDetailBinding
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzesListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

typealias FragmentBottomSheetDialogDetailAnalyzeListener = (analyze:ResponseServerCatalog) -> Unit

class FragmentBottomSheetDialogDetailAnalyze(private val analyze:ResponseServerCatalog): BottomSheetDialogFragment() {

    lateinit var binding:FragmentAnalyzeDetailBinding

    private var listeners = mutableListOf<FragmentBottomSheetDialogDetailAnalyzeListener>()

    fun addListener(listener: FragmentBottomSheetDialogDetailAnalyzeListener){
        listeners.add(listener)
    }

    fun removeListener(listener: FragmentBottomSheetDialogDetailAnalyzeListener){
        listeners.remove(listener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyzeDetailBinding.inflate(inflater)
        Log.d("BottomSheetTest","create bottom sheet binding")
        return binding.root
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding){
            this.nameTv.text = analyze.name
            this.addBut.text = this.addBut.text.toString() + analyze.price + "₽"
            this.timeResult.text = analyze.time_result
            this.descriptionTv.text = analyze.description
            this.bioMaterialTv.text = analyze.bio
            this.preparationTv.text = analyze.preparation

            this.addBut.setOnClickListener {
                listeners.forEach {
                    it.invoke(analyze)
                }
                this@FragmentBottomSheetDialogDetailAnalyze.dismiss()

            }
            this.dissmisIv.setOnClickListener {
                this@FragmentBottomSheetDialogDetailAnalyze.dismiss()
            }
        }



    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

}