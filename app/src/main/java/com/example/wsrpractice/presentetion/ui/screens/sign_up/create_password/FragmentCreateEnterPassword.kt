package com.example.wsrpractice.presentetion.ui.screens.sign_up.create_password

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.wsrpractice.App
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentCreateEnterPasswordBinding
import com.example.wsrpractice.presentetion.ui.screens.sign_up.RegistrationViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.RegistrationViewModelFactory
import com.example.wsrpractice.presentetion.ui.screens.Screens

class FragmentCreateEnterPassword: BaseFragmentMvvm<FragmentCreateEnterPasswordBinding, RegistrationViewModel>(
    FragmentCreateEnterPasswordBinding::inflate
) {

    override val viewModel by viewModels<RegistrationViewModel> {
        RegistrationViewModelFactory(requireContext())
    }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInputNumber()

        binding.skipTv.setOnClickListener {
            // todo navigate to create user data fragment
        }


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
