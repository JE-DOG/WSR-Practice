package com.example.wsrpractice.presentetion.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.databinding.FragmentAnalezyBinding
import com.example.wsrpractice.presentetion.mvvm.AnalyzesViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.AnalyzesViewModelFactory
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzesAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvAnalyzesListener
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvNewsAdapter
import com.example.wsrpractice.presentetion.ui.adapters.recyclerView.RcvSearchAnalyzesAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class FragmentAnalyze:  Fragment() {

    lateinit var binding:FragmentAnalezyBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initChipGroupAnalysisCategory()
        initNewsRcv()
        initAnalyzeRcv()
        initSearchEditText()


    }

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
            //todo add analyze to basket
            viewModel.addPrice(it.price)


            true
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
    }

    @SuppressLint("SetTextI18n")
    private fun initAnalyzeRcv(){
        val rcv = binding.rcvAnalazy
        val analyzesList = viewModel.analyzes
        val adapter = RcvAnalyzesAdapter(object : RcvAnalyzesListener {
            @SuppressLint("SetTextI18n")
            override fun buttonAddClick(analyze: ResponseServerCatalog,isSelected:Boolean) {
                viewModel.addPrice(analyze.price)
                //todo add analyze to basket
            }

            override fun onItemClick(analyze: ResponseServerCatalog) {
                createBottomSheetDialogAnalyzeDetail(analyze = analyze)

            }

        })
        val analyzesCategory = mutableListOf<ResponseServerCatalog>()
        rcv.adapter = adapter

        viewModel.category.observe(viewLifecycleOwner) { category ->
            lifecycleScope.launch {
                analyzesList.value?.map {
                    Log.d("RcvTest","${it.category} \n $category")
                    if (it.category == category) {
                        analyzesCategory.add(it)
                    }
                }
            }
            Log.d("RcvTest", analyzesCategory.toString())
            adapter.analyzes = analyzesCategory
        }

        viewModel._fullPriceLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            binding.priceTv.text = "$it ₽"

            if(it > 0){
                binding.basketLayout.visibility = View.VISIBLE
            }else{
                binding.basketLayout.visibility = View.GONE
            }
        }

        binding.promotionAndNewsTv.setOnClickListener {
            viewModel.removePrice()
        }

        analyzesList.observe(viewLifecycleOwner){
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

            responseServer.forEach {

                if (it == responseServer.first()){
                    chipGroup.addView(Chip(requireContext()).apply {
                        this.id = idPopularCategory
                        this.text = it
                        this.setTextColor(resources.getColorStateList(R.color.white))
                        this.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_true)
                    })
                    wasChipChecked = requireActivity().findViewById(idPopularCategory)
                    Log.d("ChipTest",wasChipChecked?.text.toString())
                }else{
                    chipGroup.addView(Chip(requireContext()).apply {
                        this.id = View.generateViewId()
                        this.text = it
                    })

            }

                Log.d("ChipTest","1")
            }
            Log.d("ChipTest","2")
            wasChipChecked!!.performClick()



        }

    }

}

fun View.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
