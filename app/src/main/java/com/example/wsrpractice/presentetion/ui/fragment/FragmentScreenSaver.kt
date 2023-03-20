package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsrpractice.App
import com.example.wsrpractice.databinding.FragmentScreenSaverBinding
import com.example.wsrpractice.presentetion.screens.Screens

class FragmentScreenSaver: Fragment() {
    private val app = App.INSTANCE

    lateinit var binding: FragmentScreenSaverBinding
    private val router = app.router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenSaverBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        timerReplaceScreenSaver(3)

    }

    fun timerReplaceScreenSaver(seconds:Long){

        object: CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                router.replaceScreen(Screens.firstOnce())
            }
        }.start()

    }

}