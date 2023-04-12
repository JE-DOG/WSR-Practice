package com.example.wsrpractice.presentetion.ui.screens.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.wsrpractice.App
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentBasketBinding
import com.example.wsrpractice.presentetion.model.Analyze
import com.example.wsrpractice.presentetion.mvvm.factory.ViewModelsFactory
import com.example.wsrpractice.presentetion.ui.screens.Screens
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzeBasketAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzeBasketListener

class FragmentBasket : BaseFragmentMvvm<FragmentBasketBinding, BasketViewModel>(
    FragmentBasketBinding::inflate
) {

    override val viewModel by viewModels<BasketViewModel>({requireActivity()}) {
        ViewModelsFactory()
    }
    lateinit var adapterAnalyze: RcvAnalyzeBasketAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcvAnalyze()
        initButtons()

        viewModel._analyzesLiveData.observe(viewLifecycleOwner){ it ->
            val price = viewModel.getPrice()
            Log.d("DataBaseTest",it.toString())

            binding.priceTv.text = "$price ₽"
            adapterAnalyze.analyzes = it.toMutableList()
        }
    }

    private fun initButtons(){
        val backBut = binding.backBut
        backBut.setOnClickListener {
            router.backTo(null)
        }

        val removeAllAnalyzeBut = binding.trashBasketBut
        removeAllAnalyzeBut.setOnClickListener {
            viewModel.removeAllAnalyze()
            adapterAnalyze.analyzes = mutableListOf()
        }

        val toCheckoutOrderBut = binding.checkoutOrderBut
        toCheckoutOrderBut.setOnClickListener {
            router.navigateTo(Screens.checkoutOrder())
        }
    }

    fun initRcvAnalyze(){
        adapterAnalyze = RcvAnalyzeBasketAdapter(object : RcvAnalyzeBasketListener{

            override fun countPatientsHasChanged(isAdd: Boolean, analyze: Analyze) {
                viewModel.setPatients(analyze,isAdd)
                adapterAnalyze.itemHasChange(analyze)

            }

            override fun removeAnalyze(analyze: Analyze) {
                viewModel.removeAnalyze(analyze)
                adapterAnalyze.itemHasRemoved(analyze)
            }

        })
        val rcv = binding.rcvAnalazy
        rcv.adapter = adapterAnalyze
    }

}