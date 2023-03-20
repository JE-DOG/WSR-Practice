package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.wsrpractice.R
import com.example.wsrpractice.databinding.FragmentCreatePatientCardBinding

class FragmentCreatePatientCard:Fragment() {

    lateinit var binding:FragmentCreatePatientCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePatientCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initInputData()


    }

    private fun initInputData(){
        val inputData = binding.inputUserDataInclude
        val inputName = inputData.inputName
        val inputSex = inputData.setGenderEt
        val inputSurname = inputData.inputSurname
        val inputLastname = inputData.inputOtchevstvo
        val inputBorn = inputData.inputDateBorn
        val createBut = binding.createBut

        inputName.addTextChangedListener {
            createBut.isEnabled = !(inputName.text.toString().isEmpty() ||
                    inputLastname.text.toString().isEmpty() ||
                    inputSex.text.toString().isEmpty() ||
                    inputBorn.text.toString().isEmpty() ||
                    inputSurname.text.toString().isEmpty())
        }
        inputSurname.addTextChangedListener {
            createBut.isEnabled = !(inputName.text.toString().isEmpty() ||
                    inputLastname.text.toString().isEmpty() ||
                    inputSex.text.toString().isEmpty() ||
                    inputBorn.text.toString().isEmpty() ||
                    inputSurname.text.toString().isEmpty())
        }
        inputLastname.addTextChangedListener {
            createBut.isEnabled = !(inputName.text.toString().isEmpty() ||
                    inputLastname.text.toString().isEmpty() ||
                    inputSex.text.toString().isEmpty() ||
                    inputBorn.text.toString().isEmpty() ||
                    inputSurname.text.toString().isEmpty())
        }
        inputBorn.addTextChangedListener {
            createBut.isEnabled = !(inputName.text.toString().isEmpty() ||
                    inputLastname.text.toString().isEmpty() ||
                    inputSex.text.toString().isEmpty() ||
                    inputBorn.text.toString().isEmpty() ||
                    inputSurname.text.toString().isEmpty())
        }
        inputSex.addTextChangedListener {
            createBut.isEnabled = !(inputName.text.toString().isEmpty() ||
                    inputLastname.text.toString().isEmpty() ||
                    inputSex.text.toString().isEmpty() ||
                    inputBorn.text.toString().isEmpty() ||
                    inputSurname.text.toString().isEmpty())
        }

        //input sex
        val genders = resources.getStringArray(R.array.gender)
        val adapterSex = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            genders
        )
        inputSex.setAdapter(adapterSex)

        inputSex.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                inputSex.showDropDown()
            }
        }
        inputSex.setOnClickListener {
            inputSex.showDropDown()
        }
    }

}