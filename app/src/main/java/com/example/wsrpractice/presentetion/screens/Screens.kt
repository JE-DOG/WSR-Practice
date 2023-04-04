package com.example.wsrpractice.presentetion.screens

import com.example.wsrpractice.presentetion.screens.analyze.FragmentAnalyze
import com.example.wsrpractice.presentetion.screens.basket.FragmentBasket
import com.example.wsrpractice.presentetion.screens.checkout_order.FragmentCheckoutOrder
import com.example.wsrpractice.presentetion.screens.sign_up.create_password.FragmentCreateEnterPassword
import com.example.wsrpractice.presentetion.screens.nothing.FragmentNothing
import com.example.wsrpractice.presentetion.screens.sign_up.once.FragmentOnceScreen
import com.example.wsrpractice.presentetion.screens.profile.FragmentProfile
import com.example.wsrpractice.presentetion.screens.sign_up.email.FragmentInputEmail
import com.example.wsrpractice.presentetion.screens.splash.FragmentScreenSaver
import com.example.wsrpractice.presentetion.screens.sign_up.verify_email.FragmentVerifyEmail
import com.example.wsrpractice.presentetion.ui.fragment.*
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun nothing() = FragmentScreen{ FragmentNothing() }
    fun checkoutOrder() = FragmentScreen { FragmentCheckoutOrder() }
    fun firstOnce() = FragmentScreen{ FragmentOnceScreen() }
    fun screenSaver() = FragmentScreen{ FragmentScreenSaver() }
    fun inputEmail() = FragmentScreen{ FragmentInputEmail() }
    fun verifyEmail() = FragmentScreen{ FragmentVerifyEmail() }
    fun createEnterPassword() = FragmentScreen{ FragmentCreateEnterPassword() }
    fun analyze() = FragmentScreen{ FragmentAnalyze() }
    fun profile() = FragmentScreen{ FragmentProfile() }

    fun toBasket() = FragmentScreen { FragmentBasket() }

}