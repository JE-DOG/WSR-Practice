package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.databinding.FragmentProfileBinding
import com.example.wsrpractice.presentetion.mvvm.ProfileViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.ProfileViewModelFactory
import kotlinx.coroutines.launch

class FragmentProfile:Fragment() {

    lateinit var binding:FragmentProfileBinding
    private val viewModel by lazy{
        ViewModelProvider(requireActivity(),ProfileViewModelFactory())[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initInputData()

    }

    private fun initInputData(){


        val userPatientCard = viewModel._liveDataPatientCard
        val serverAnswer = viewModel._liveDataServerAnswer

        val inputData = binding.inputUserData
        val setGenderEditText = inputData.setGenderEt
        val inputName = inputData.inputName
        val inputSurname = inputData.inputSurname
        val inputLastName = inputData.inputOtchevstvo
        val inputDataBorn = inputData.inputDateBorn
        val saveBut = binding.saveBut

        //заполнение строк
        userPatientCard.observe(viewLifecycleOwner){
            inputName.setText(userPatientCard.value?.firstname)
            inputSurname.setText(userPatientCard.value?.middlename)
            inputLastName.setText(userPatientCard.value?.lastname)
            inputDataBorn.setText(userPatientCard.value?.bith)
            setGenderEditText.setText(userPatientCard.value?.pol)
        }

        serverAnswer.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
        }




        //set genders
        val genders = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,genders)
        setGenderEditText.setAdapter(adapter)

        setGenderEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                setGenderEditText.text = null
                setGenderEditText.showDropDown()

            }
        }

        setGenderEditText.setOnClickListener {

            setGenderEditText.showDropDown()

        }

        Log.d("emptyTest","${(inputName.toString().isEmpty() ||
                setGenderEditText.toString().isEmpty() ||
                inputDataBorn.toString().isEmpty() ||
                inputLastName.toString().isEmpty() ||
                inputSurname.toString().isEmpty())}")
        //check on empty stroke edit texts
        if(inputName.toString().isEmpty() ||
                setGenderEditText.toString().isEmpty() ||
                inputDataBorn.toString().isEmpty() ||
                inputLastName.toString().isEmpty() ||
                inputSurname.toString().isEmpty()){
            saveBut.text = "Создание карты"
        }

        saveBut.setOnClickListener {
            val name = inputName.text.toString()
            val surname = inputSurname.text.toString()
            val lastname = inputLastName.text.toString()
            val dataBorn = inputDataBorn.text.toString()
            val gender = setGenderEditText.text.toString()

            val patientCard = UserPatientCardNetwork(
                firstname = name,
                middlename = surname,
                lastname = lastname,
                bith = dataBorn,
                pol = gender
            )

            Log.d("UserPatientCardTest",patientCard.toString())


            //todo как то сделать,так чтобы узнавать есть ли аккаунт
            lifecycleScope.launch{
                viewModel.savePatientCard(patientCard = patientCard,false)
            }




        }

    }

}