package com.example.wsrpractice.presentetion.screens

import com.example.wsrpractice.presentetion.ui.fragment.*
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun nothing() = FragmentScreen{ FragmentNothing() }

    fun firstOnce() = FragmentScreen{ FragmentOnceScreen() }
    fun screenSaver() = FragmentScreen{ FragmentScreenSaver() }
    fun inputEmail() = FragmentScreen{ FragmentInputEmail() }
    fun verifyEmail() = FragmentScreen{ FragmentVerifyEmail() }
    fun createEnterPassword() = FragmentScreen{ FragmentCreateEnterPassword() }
    fun analyze() = FragmentScreen{ FragmentAnalyze() }
    fun profile() = FragmentScreen{ FragmentProfile() }

}