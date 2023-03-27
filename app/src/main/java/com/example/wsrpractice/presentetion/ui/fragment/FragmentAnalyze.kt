package com.example.wsrpractice.presentetion.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.wsrpractice.App
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.databinding.FragmentAnalezyBinding
import com.example.wsrpractice.presentetion.mvvm.AnalyzesViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.AnalyzesViewModelFactory
import com.example.wsrpractice.presentetion.screens.Screens
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzesAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzesListener
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvNewsAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvSearchAnalyzesAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class FragmentAnalyze:  Fragment() {
    lateinit var adapter: RcvAnalyzesAdapter
    lateinit var binding:FragmentAnalezyBinding
    private val router = App.INSTANCE.router
    private val viewModel: AnalyzesViewModel by viewModels({requireActivity()}){
        AnalyzesViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getAnalyzesCategory()

        binding = FragmentAnalezyBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initChipGroupAnalysisCategory()
        initNewsRcv()
        initAnalyzeRcv()
        initSearchEditText()
        initRefresh()
        initButtons()

    }

    private fun initButtons(){
        // basket but
        val toBasketBut = binding.basketBut

        toBasketBut.setOnClickListener {
            viewModel.saveAnalyzes()
            router.navigateTo(Screens.toBasket())
        }


    }



    private fun initRefresh(){
        val refresh = binding.refresh
        refresh.setOnRefreshListener {
            viewModel.getAnalyzesCategory()
            refresh.isRefreshing = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        biometricRegistrate()
    }

//    private fun biometricRegistrate(){
//        val executor = ContextCompat.getMainExecutor(requireActivity())
//
//        val biometricPrompt = androidx.biometric.BiometricPrompt(
//            this,
//            executor,
//            object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
//
//                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    val router = App.INSTANCE.router
//                    router.navigateTo(Screens.profile())
//                }
//
//            }
//        )
//
//        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
//            .setDescription("Dokazhi chto ti chelovek")
//            .setSubtitle("kaka")
//            .setTitle("registrate")
//            .setNegativeButtonText("ebat ti lox")
//            .build()
//
//        biometricPrompt.authenticate(promptInfo)
//    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditText(){
        val adapterSearch = RcvSearchAnalyzesAdapter()
        val searchAnalyzesRcv = binding.searchAnalyseRcv
        searchAnalyzesRcv.adapter = adapterSearch

        val editText = binding.searchAnalyse
        val back = binding.backFromSearchAnalyzeTv




        editText.setOnFocusChangeListener { v, hasFocus ->
            Log.d("EditTextTest",hasFocus.toString())
            if (!hasFocus){
                onlySearchEditTextVisible(visible = false)
                editText.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(requireContext(),R.drawable.ic_search),
                    null,null,null
                )
            }

        }



        editText.setOnTouchListener { v, event ->
            val LEFT_DRAWABLE = 0
            val TOP_DRAWABLE = 1
            val RIGHT_DRAWABLE = 2
            val BOTTOM_DRAWABLE = 3

            onlySearchEditTextVisible(visible = true)
            editText.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(requireContext(),R.drawable.ic_search),null,
                ContextCompat.getDrawable(requireContext(),R.drawable.ic_rectangle_gray),null
            )

            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= (editText.right - editText.compoundDrawables[RIGHT_DRAWABLE].bounds.width())) {
                    editText.setText("")
                    return@setOnTouchListener true
                }
            }

            false
        }

        back.setOnClickListener {
            onlySearchEditTextVisible(false)
            editText.setText("")
            editText.clearFocus()
            editText.hideKeyboard()
        }

        editText.addTextChangedListener {
            val searchText = it.toString().toCharArray()
            val analyzes = viewModel.analyzes.value
            val findAnalyzes= mutableListOf<ResponseServerCatalog>()

            analyzes?.forEach {
                val name = it.name.toCharArray()
                for (letter in name) {
                    for (letterSearch in searchText) {
                        if (letter == letterSearch) {
                            findAnalyzes.add(it)
                        }
                    }
                }
            }

            adapterSearch.analyzes = findAnalyzes

        }



    }

    private fun onlySearchEditTextVisible(visible: Boolean){
        if (visible) {
            with(binding) {
                this.contentMotionLayout.visibility = View.INVISIBLE
                this.searchAnalyseRcv.visibility = View.VISIBLE

                this.backFromSearchAnalyzeTv.visibility = View.VISIBLE

                this.searchAnalyse.hint = ""

            }
        }else{
            with(binding) {
                this.contentMotionLayout.visibility = View.VISIBLE
                this.searchAnalyseRcv.visibility = View.INVISIBLE

                this.backFromSearchAnalyzeTv.visibility = View.GONE

                this.searchAnalyse.hint = "Искать анализы"
            }

        }
    }


    private fun createBottomSheetDialogAnalyzeDetail(analyze:ResponseServerCatalog){
        val listener:FragmentBottomSheetDialogDetailAnalyzeListener = {
            analyze.isSelected = !analyze.isSelected
            //todo add analyze to basket
            if (analyze.isSelected){
                viewModel.addAnalyze(it)
            }else{
                viewModel.removeAnalyze(it)
            }
            adapter.changeItem(it)
        }
        val bottomSheet = FragmentBottomSheetDialogDetailAnalyze(analyze)
        bottomSheet.addListener(listener)
        bottomSheet.show(parentFragmentManager,"AnalyzeDetail")



        Log.d("BottomSheetTest","create bottom sheet from fragment")
    }


    private fun initNewsRcv() {
        val adapter = RcvNewsAdapter()
        val rcv = binding.rcvNews

        rcv.adapter = adapter

        rcv.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.refresh.isEnabled = false
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.refresh.isEnabled = true
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initAnalyzeRcv(){
        val rcv = binding.rcvAnalazy
        val analyzesList = viewModel.analyzes
        adapter = RcvAnalyzesAdapter(object : RcvAnalyzesListener {
            override fun buttonAddClick(analyze: ResponseServerCatalog) {

                analyze.isSelected = !analyze.isSelected
                if(analyze.isSelected) {
                    viewModel.addAnalyze(analyze)
                }else{
                    viewModel.removeAnalyze(analyze)
                }
                adapter.changeItem(analyze)
            }

            override fun onItemClick(analyze: ResponseServerCatalog) {
                adapter.changeItem(analyze)
                createBottomSheetDialogAnalyzeDetail(analyze = analyze)
            }

        })
        var analyzesCategory = mutableListOf<ResponseServerCatalog>()
        rcv.adapter = adapter

        rcv.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topVerticalPosition =
                    if (recyclerView == null || recyclerView.childCount == 0 ){
                        0
                    }else{
                        recyclerView.getChildAt(0).top
                    }

                binding.refresh.isEnabled = topVerticalPosition >= 0
            }

        })

        viewModel.category.observe(viewLifecycleOwner) { category ->

            binding.promotionAndNewsTv.text = category

            lifecycleScope.launch {
                analyzesCategory = mutableListOf()
                analyzesList.value?.map {
//                    Log.d("RcvTest","${it.category} \n $category")
                    if (it.category == category) {
                        Log.d("ChipRcvTest", "added to list for analyze list:\n$it")
                        analyzesCategory.add(it)
                    }
                }
            }
            Log.d("ChipRcvTest", "refresh man cool")
            Log.d("ChipRcvTest","${analyzesCategory[0].category}\n${category}\n$analyzesCategory")

            adapter.analyzes = analyzesCategory
        }

        viewModel._selectedAnalyzeLiveData.observe(viewLifecycleOwner){analyze ->

            var price = 0
            analyze?.forEach {
                price += it.price.toInt()
            }
            Toast.makeText(requireContext(), price.toString(), Toast.LENGTH_SHORT).show()
            binding.priceTv.text = "$price ₽"

            if(price > 0){
                binding.basketLayout.visibility = View.VISIBLE
            }else{
                binding.basketLayout.visibility = View.GONE
            }
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun initChipGroupAnalysisCategory(){

        val idPopularCategory = View.generateViewId()
        var wasChipChecked:Chip? = null
        val chipGroup = binding.categoryAnalyzeChipGroup

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            Log.d("RcvTest",viewModel.analyzes.value.toString())
            wasChipChecked?.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_false)
            wasChipChecked?.setTextColor(resources.getColorStateList(R.color.black))

            val checkedChip = requireActivity().findViewById<Chip>(group.checkedChipId)

            checkedChip.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_true)
            checkedChip.setTextColor(resources.getColorStateList(R.color.white))

            wasChipChecked = checkedChip

            viewModel.setCategory(checkedChip.text.toString())
            Log.d("RcvTest",viewModel.category.value.toString())
        }


        viewModel.analyzes.observe(viewLifecycleOwner) { catalogs ->
            val responseServer = catalogs.map { it.category }.toSet().toList()
            chipGroup.removeAllViews()

            responseServer.forEach {

                if (it == responseServer.first()){
                    chipGroup.addView(Chip(requireContext()).apply {
                        this.id = idPopularCategory
                        this.text = it
                        chipMinHeight = 140f
                        chipEndPadding = 25f
                        chipStartPadding = 25f
                        this.setTextColor(resources.getColorStateList(R.color.white))
                        this.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_true)
                    })
                    wasChipChecked = requireActivity().findViewById(idPopularCategory)
                    Log.d("ChipTest",wasChipChecked?.text.toString())
                }else{
                    chipGroup.addView(Chip(requireContext()).apply {
                        this.id = View.generateViewId()
                        this.text = it
                        chipMinHeight = 140f
                    })

            }

                Log.d("ChipTest","1")
            }
            Log.d("ChipTest","2")
            wasChipChecked!!.performClick()



        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initMotionLayout(){
        val scroll = binding.contentMotionLayout
    }

}

fun View.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
