package com.example.wsrpractice.presentetion.ui.adapters.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.databinding.RcvSearchAnalyzesListItemBinding

class RcvSearchAnalyzesAdapter:RecyclerView.Adapter<RcvSearchAnalyzesAdapter.SearchAnalyzesHolder>() {

    var analyzes = mutableListOf<ResponseServerCatalog>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class SearchAnalyzesHolder(val binding:RcvSearchAnalyzesListItemBinding):RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(analyze:ResponseServerCatalog)= with(binding){
            this.name.text = analyze.name
            this.price.text = analyze.price + "₽"
            this.timeResult.text = analyze.time_result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAnalyzesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvSearchAnalyzesListItemBinding.inflate(inflater,parent,false)
        return SearchAnalyzesHolder(binding)
    }


    override fun onBindViewHolder(holder: SearchAnalyzesHolder, position: Int) {
        val analyze = analyzes[position]
        holder.bind(analyze)

    }

    override fun getItemCount() = analyzes.size

}