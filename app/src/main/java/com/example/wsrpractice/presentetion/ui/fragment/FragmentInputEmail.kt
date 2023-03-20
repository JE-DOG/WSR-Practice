package com.example.wsrpractice.presentetion.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.App
import com.example.wsrpractice.databinding.FragmentInputEmailBinding
import com.example.wsrpractice.presentetion.mvvm.RegistrationViewModel
import com.example.wsrpractice.presentetion.screens.Screens
import kotlinx.coroutines.launch

class FragmentInputEmail: Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
    }
    lateinit var binding:FragmentInputEmailBinding
    private val app = App.INSTANCE
    private val router = app.router
//    private val viewModel by viewModels<> {  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputEmailBinding.inflate(inflater)
        return binding.root
    }

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