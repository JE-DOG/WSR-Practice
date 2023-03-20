package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wsrpractice.R
import com.example.wsrpractice.databinding.TestChipgroupBinding
import com.google.android.material.chip.Chip

class FragmentTEst: Fragment() {

    lateinit var binding: TestChipgroupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestChipgroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initChipGroup()

    }

    fun initChipGroup(){

        var wasCheckedChip:Chip? = null

        val molResponseServer = arrayOf(
            "mashina",
            "tochka",
            "chupil"
        )

        val chipGroup = binding.categoryAnalyzeChipGroup

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            wasCheckedChip?.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_false)
            wasCheckedChip?.setTextColor(resources.getColorStateList(R.color.black))

            val checkedChip = requireActivity().findViewById<Chip>(group.checkedChipId)

            checkedChip.chipBackgroundColor = resources.getColorStateList(R.color.chip_enable_true)
            checkedChip.setTextColor(resources.getColorStateList(R.color.white))

            Toast.makeText(requireContext(), checkedIds.toString(), Toast.LENGTH_SHORT).show()


            wasCheckedChip = checkedChip
        }

        molResponseServer.forEach {
            val chipId = View.generateViewId()

            chipGroup.addView(
                Chip(requireContext()).apply {
                    id = chipId
                    text = it
                })
        }

    }

}