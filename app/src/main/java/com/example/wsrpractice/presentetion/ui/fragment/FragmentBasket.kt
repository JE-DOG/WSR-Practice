package com.example.wsrpractice.presentetion.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wsrpractice.App
import com.example.wsrpractice.databinding.FragmentBasketBinding
import com.example.wsrpractice.presentetion.model.Analyze
import com.example.wsrpractice.presentetion.mvvm.BasketViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.AnalyzesViewModelFactory
import com.example.wsrpractice.presentetion.screens.Screens
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzeBasketAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzeBasketListener

class FragmentBasket : Fragment() {

    private val viewModel by viewModels<BasketViewModel>({requireActivity()}) {
        AnalyzesViewModelFactory()
    }
    private val router = App.INSTANCE.router
    lateinit var binding:FragmentBasketBinding
    lateinit var adapterAnalyze: RcvAnalyzeBasketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel.getAnalyzes()
        binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

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