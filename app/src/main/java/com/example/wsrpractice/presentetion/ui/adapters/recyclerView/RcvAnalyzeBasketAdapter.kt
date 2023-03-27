package com.example.wsrpractice.presentetion.ui.adapters.recyclerView

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsrpractice.databinding.FragmentBasketBinding
import com.example.wsrpractice.databinding.RcvItemListBasketBinding
import com.example.wsrpractice.presentetion.model.Analyze

class RcvAnalyzeBasketAdapter: RecyclerView.Adapter<RcvAnalyzeBasketAdapter.AnalyzeViewHolder>() {

    var analyzes = emptyList<Analyze>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class AnalyzeViewHolder(private val binding:RcvItemListBasketBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun bind(analyze: Analyze){
            binding.priceTv.text = analyze.price + " ₽"
            binding.name.text = analyze.name
            binding.patientsTv.text = if (analyze.patients > 1){ "${analyze.patients} Пациентов"}else{"1 Пациент"}

            binding.patientsCounter.setOnTouchListener { v, event ->

                val x = event.x

                val fullX = v.width / 2

                if (event.action == MotionEvent.ACTION_UP){

                    if (x >= fullX){
                        Log.d("TouchTest","patient added\nx:$x\nfullx / 2:$fullX ")

                        analyze.patients--
                    }else{
                        Log.d("TouchTest","patient removed\nx:$x\nfullx / 2:$fullX ")

                        analyze.patients++
                    }

                }
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyzeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemListBasketBinding.inflate(inflater,parent,false)
        return AnalyzeViewHolder(binding)
    }

    override fun getItemCount(): Int = analyzes.size

    override fun onBindViewHolder(holder: AnalyzeViewHolder, position: Int) {
        val analyze = analyzes[position]
        holder.bind(analyze)
    }

}