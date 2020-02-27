package com.example.mazdaservice.NewsView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.R

// Создаем адаптер для всех новостей
class NewsAdapter(private var news: Array<NewsElement>) : RecyclerView.Adapter<NewsHolder>(){

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item, null)
        return NewsHolder(v)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = news[position]
        holder.image.setImageResource(news.image)

        holder.setItemClickListener(object : NewsHolder.ItemClickListener{
            override fun onItemClick(v : View, pos : Int){

            }
        })
    }
}
