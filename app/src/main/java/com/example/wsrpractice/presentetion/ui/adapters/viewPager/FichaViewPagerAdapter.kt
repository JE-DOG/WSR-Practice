package com.example.wsrpractice.presentetion.ui.adapters.viewPager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.wsrpractice.databinding.FragmentFirstFichaBinding
import kotlinx.coroutines.internal.artificialFrame

class FichaViewPagerAdapter(activity:FragmentActivity,private val layoutList: List<Int>): FragmentStateAdapter(activity) {

    override fun getItemCount() = layoutList.size

    override fun createFragment(position: Int): Fragment {
        return Fragment(layoutList[position])
    }
}

class FichaViewPagerRecyclerAdapter(private var fichaList: List<Ficha>): RecyclerView.Adapter<FichaViewPagerRecyclerAdapter.FichaViewHolder>(){

    inner class FichaViewHolder(private val binding: FragmentFirstFichaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ficha: Ficha){
            binding.fichaTv.text = ficha.name
            binding.deatailFichaTv.text = ficha.details
            binding.imageView3.setImageResource(ficha.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FichaViewHolder {
        return FichaViewHolder(
            FragmentFirstFichaBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int = fichaList.size

    override fun onBindViewHolder(holder: FichaViewHolder, position: Int) {
        holder.bind(fichaList[position])
    }

    data class Ficha(
        val name: String,
        val details: String,
        val image: Int
    )
}
