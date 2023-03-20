package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wsrpractice.App
import com.example.wsrpractice.databinding.FragmentCreateEnterPasswordBinding
import com.example.wsrpractice.presentetion.mvvm.RegistrationViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.RegistrationViewModelFactory
import com.example.wsrpractice.presentetion.screens.Screens
import java.lang.reflect.Array.set

class FragmentCreateEnterPassword: Fragment() {

    lateinit var binding: FragmentCreateEnterPasswordBinding
    private val viewModel by viewModels<RegistrationViewModel> {
        RegistrationViewModelFactory(requireContext())
    }
    private val app = App.INSTANCE
    private val router = app.router
    private var password = ""
        set(value) {
            if (value.length == 4){
                viewModel.savePassword(value)
                router.replaceScreen(Screens.analyze())
            }
            if (value.length in 0..4){
                field = value
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEnterPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInputNumber()

        binding.skipTv.setOnClickListener {
            // todo navigate to create user data fragment
        }


    }

    private fun btnClick(view: View){
        (view as Button)
    }

    private fun initInputNumber(){

        val inputNumberLayout = binding.include
        val numbersBut = arrayOf(
            inputNumberLayout.num0,
            inputNumberLayout.num1,
            inputNumberLayout.num2,
            inputNumberLayout.num3,
            inputNumberLayout.num4,
            inputNumberLayout.num5,
            inputNumberLayout.num6,
            inputNumberLayout.num7,
            inputNumberLayout.num8,
            inputNumberLayout.num9)
        val dots = arrayOf(
            binding.dots1,
            binding.dots2,
            binding.dots3,
            binding.dots4
        )



        numbersBut[0].setOnClickListener {
            password += "0"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[1].setOnClickListener {
            password += "1"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[2].setOnClickListener {
            password += "2"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[3].setOnClickListener {
            password += "3"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[4].setOnClickListener {
            password += "4"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[5].setOnClickListener {
            password += "5"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[6].setOnClickListener {
            password += "6"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[7].setOnClickListener {
            password += "7"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[8].setOnClickListener {
            password += "8"

            dots[password.length - 1].isEnabled = true
        }
        numbersBut[9].setOnClickListener {
            password += "9"

            dots[password.length - 1].isEnabled = true
        }

        inputNumberLayout.deleteBut.setOnClickListener {
            if(password.length in  1..4) {
                dots[password.length - 1].isEnabled = false

                Log.d("delete","before length:${password.length - 1}")
                password = password.dropLast(1)
                Log.d("delete","after length:${password.length - 1}")

            }
            Log.d("delete","password:$password")

        }



    }

}
