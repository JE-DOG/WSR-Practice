package com.example.wsrpractice.presentetion.ui.screens.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.wsrpractice.App
import com.example.wsrpractice.core.ui.BaseFragment
import com.example.wsrpractice.databinding.FragmentScreenSaverBinding
import com.example.wsrpractice.presentetion.ui.screens.Screens

class FragmentScreenSaver: BaseFragment<FragmentScreenSaverBinding>(
    FragmentScreenSaverBinding::inflate
) {

    private val seconds_for_replace = 3L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        timerReplaceScreenSaver(seconds_for_replace)

    }

    private fun timerReplaceScreenSaver(seconds:Long){

        object: CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                router.replaceScreen(Screens.firstOnce())
            }
        }.start()

    }

}