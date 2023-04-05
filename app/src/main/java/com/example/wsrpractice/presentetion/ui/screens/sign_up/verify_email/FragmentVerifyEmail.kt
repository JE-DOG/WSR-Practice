package com.example.wsrpractice.presentetion.ui.screens.sign_up.verify_email

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.App
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentVerifyEmailBinding
import com.example.wsrpractice.presentetion.ui.screens.sign_up.RegistrationViewModel
import com.example.wsrpractice.presentetion.ui.screens.Screens
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FragmentVerifyEmail: BaseFragmentMvvm<FragmentVerifyEmailBinding, RegistrationViewModel>(
    FragmentVerifyEmailBinding::inflate
) {

    override val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOtp()
    }

    private fun  initOtp(){

        val num1 = binding.num1
        val num2 = binding.num2
        val num3 = binding.num3
        val num4 = binding.num4

        num1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0){
                    num2.requestFocus()
                }
            }
        })
        num2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0){
                    num3.requestFocus()
                }else if(s.isEmpty()){
                    num1.requestFocus()
                }
            }
        })
        num3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0){
                    num4.requestFocus()
                }else if(s.isEmpty()){
                    num3.requestFocus()
                }
            }
        })
        num4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0){
                    val verifyCode = num1.text.toString() + num2.text.toString() + num3.text.toString() + num4.text.toString()
                    Log.d("verifyCode",verifyCode)
                    val response = lifecycleScope.async {
                        val result = viewModel.textChangedVerifyCode(code = verifyCode)
                        result
                    }

                    lifecycleScope.launch {
                        if (response.await()) {
                            router.replaceScreen(Screens.createEnterPassword())
                        } else {
                            binding.textView6.text = "Попробуйте еще раз"
                        }
                    }
                }else if(s.isEmpty()){
                    num3.requestFocus()
                }

            }
        })


    }

}