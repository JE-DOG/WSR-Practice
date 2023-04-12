package com.example.wsrpractice.presentetion.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.wsrpractice.App
import com.example.wsrpractice.R
import com.example.wsrpractice.databinding.ActivityMainBinding
import com.example.wsrpractice.presentetion.ui.screens.Screens
import com.example.wsrpractice.presentetion.ui.screens.analyze.FragmentAnalyze
import com.example.wsrpractice.presentetion.ui.screens.analyze.bottom_sheet.FragmentBottomSheetDialogDetailAnalyze
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.main.FragmentCheckoutOrder
import com.example.wsrpractice.presentetion.ui.screens.checkout_order.map.FragmentMapSetUserAddress
import com.example.wsrpractice.presentetion.ui.screens.nothing.FragmentNothing
import com.example.wsrpractice.presentetion.ui.screens.profile.FragmentProfile
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private val app = App.INSTANCE

    lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this,R.id.fragmentContainerView)
    private val  router = app.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        router.replaceScreen(Screens.analyze())

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, FragmentCheckoutOrder())
            .commit()
        initBottomNav()

        supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->

            when(fragment){
                is FragmentAnalyze -> {
                    binding.bottomNav.isVisible = true
                }

                is FragmentBottomSheetDialogDetailAnalyze -> {
                    binding.bottomNav.isVisible = true
                }

                is FragmentProfile -> {
                    binding.bottomNav.isVisible = true

                }

                is FragmentNothing ->{
                    binding.bottomNav.isVisible = true

                }


                else -> binding.bottomNav.isVisible = false
            }
        }

    }

    private fun initBottomNav() {

        binding.bottomNav.setOnItemSelectedListener {


            when(it.itemId){

                R.id.bn_analazy ->{

                    router.navigateTo(Screens.analyze())

                }

                R.id.bn_user ->{

                    router.navigateTo(Screens.profile())

                }

                else -> {
                    router.navigateTo(Screens.nothing())
                }

            }

            true
        }

    }

    override fun onResume() {
        super.onResume()
        app.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        app.navigatorHolder.removeNavigator()
    }

}