package com.example.wsrpractice.presentetion.ui.screens.sign_up.email

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.App
import com.example.wsrpractice.core.ui.BaseFragmentMvvm
import com.example.wsrpractice.databinding.FragmentInputEmailBinding
import com.example.wsrpractice.presentetion.ui.screens.sign_up.RegistrationViewModel
import com.example.wsrpractice.presentetion.ui.screens.Screens
import kotlinx.coroutines.launch

class FragmentInputEmail: BaseFragmentMvvm<FragmentInputEmailBinding, RegistrationViewModel>(
    FragmentInputEmailBinding::inflate
) {

    override val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
    }
//    private val viewModel by viewModels<> {  }


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputEmail = binding.inputEmailEditText

        inputEmailEditText(inputEmail)

        binding.nextWithEmailBut.setOnClickListener {
            val email = inputEmail.text.toString()
            lifecycleScope.launch {
                val response = viewModel.clickSendEmail(email = email)

                if (response) {
                    router.navigateTo(Screens.verifyEmail())
                } else {
                    binding.errorTv.text = "Попробуйте еще раз"
                }
            }

        }
    }

    private fun inputEmailEditText(inputEmail:EditText){


        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                val buttonNextWithEmail = binding.nextWithEmailBut
                Log.d("buttonNextWithEmailEnable",input)

                buttonNextWithEmail.isEnabled = Patterns.EMAIL_ADDRESS.matcher(input).matches()
            }
        })

    }

}