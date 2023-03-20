package com.example.wsrpractice.presentetion.ui.adapters.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsrpractice.databinding.RcvItemListNewsBinding
import com.example.wsrpractice.presentetion.ui.model.News


class RcvNewsAdapter: RecyclerView.Adapter<RcvNewsAdapter.NewsHolder>() {

    var news = arrayOf(
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб",""),
        News(0,"Анализ мочи","Для очень богатых дядь","0.9М Руб","")
    )

    class NewsHolder(val binding: RcvItemListNewsBinding):RecyclerView.ViewHolder(binding.root) {


        fun bind(news: News) = with(binding) {
            this.descriptionTv.text = news.description
            this.nameTv.text = news.name
            this.priceTv.text = news.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemListNewsBinding.inflate(inflater,parent,false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size



}