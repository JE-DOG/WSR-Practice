package com.example.wsrpractice.presentetion.ui.adapters.recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.ResponseServerCatalog
import com.example.wsrpractice.databinding.RcvItemListAnalezyBinding
import org.apache.http.conn.ConnectTimeoutException

interface RcvAnalyzesListener{

    fun buttonAddClick(analyze: ResponseServerCatalog)

    fun onItemClick(analyze:ResponseServerCatalog)

}

class RcvAnalyzesAdapter(private val listener:RcvAnalyzesListener):RecyclerView.Adapter<RcvAnalyzesAdapter.AnalyzeHolder>() {

    var analyzes:MutableList<ResponseServerCatalog> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
        field = value

        notifyDataSetChanged()
    }


    fun changeItem(item: ResponseServerCatalog){
        val itemIndex = analyzes.indexOf(item)
        analyzes[itemIndex] = item
        notifyItemChanged(itemIndex)
    }

    inner class AnalyzeHolder(val binding: RcvItemListAnalezyBinding):RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(analezy: ResponseServerCatalog) = with(binding){
            this.daysTv.text = analezy.time_result
            this.nameTv.text = analezy.name
            priceTv.text = analezy.price + " Р"
            addBut.isSelected = analezy.isSelected
            changeAddBut()

            this.addBut.setOnClickListener {
                Log.d("selectRcvTest", addBut.isSelected.toString())
                listener.buttonAddClick(analezy)
                changeAddBut()
                Log.d("selectRcvTest", addBut.isSelected.toString())

            }
            this.root.setOnClickListener {
                listener.onItemClick(analezy)

            }



        }

        private fun changeAddBut(){
            val addBut = binding.addBut
            if (addBut.isSelected) {
                addBut.text = "Убрать"
                addBut.setTextColor(Color.parseColor("#1A6FEE"))
            }else{
                addBut.text = "Добавить"
                addBut.setTextColor(Color.WHITE)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyzeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemListAnalezyBinding.inflate(inflater,parent,false)
        return AnalyzeHolder(binding)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: AnalyzeHolder, position: Int) {
        val analyze = analyzes[position]
        holder.bind(analyze)
    }

    override fun getItemCount() = analyzes.size


}
