package com.example.wsrpractice.presentetion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.wsrpractice.App
import com.example.wsrpractice.R
import com.example.wsrpractice.databinding.FragmentOnceScreenBinding
import com.example.wsrpractice.presentetion.screens.Screens
import com.example.wsrpractice.presentetion.ui.adapters.viewPager.FichaViewPagerAdapter
import com.example.wsrpractice.presentetion.ui.adapters.viewPager.FichaViewPagerRecyclerAdapter
import com.example.wsrpractice.presentetion.ui.fragment.ficha.FragmentFirstFicha
import com.example.wsrpractice.presentetion.ui.fragment.ficha.FragmentSecondFicha
import com.example.wsrpractice.presentetion.ui.fragment.ficha.FragmentThirdFicha


class FragmentOnceScreen: Fragment() {

    lateinit var binding: FragmentOnceScreenBinding
    val app = App.INSTANCE
    val router = app.router



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnceScreenBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewPager()

        val skipTv = binding.skipTv

        skipTv.setOnClickListener {
            router.replaceScreen(Screens.inputEmail())
        }
    }

    private fun initViewPager() {
        val viewPager = binding.viewPager
        val pagerAdapter = FichaViewPagerAdapter(requireActivity(), listOf(R.layout.fragment_first_ficha,R.layout.fragment_second_ficha,R.layout.fragment_third_ficha))
        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = FichaViewPagerRecyclerAdapter(listOf(
            FichaViewPagerRecyclerAdapter.Ficha("Анализы","Экспресс сбор и получение проб",R.drawable.ic_first),
            FichaViewPagerRecyclerAdapter.Ficha("Уведомления","Вы быстро узнаете о результатах",R.drawable.ic_second),
            FichaViewPagerRecyclerAdapter.Ficha("Мониторинг","Наши врачи всегда наблюдают \n" +
                    "за вашими показателями здоровья",R.drawable.ic_third)
        ))

        val viewPagerListener = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2){
                    binding.skipTv.text = "Завершить"
                }else{
                    binding.skipTv.text = "Пропустить"

                }
            }
        }

        dotsIndicator.attachTo(viewPager)
        viewPager.registerOnPageChangeCallback(viewPagerListener);
    }
}