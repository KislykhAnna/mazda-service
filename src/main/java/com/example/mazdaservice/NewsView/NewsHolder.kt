package com.example.mazdaservice.NewsView

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.R

// Создаем Holder, "удерживает" каждый отдельный элемент группы Новостей
class NewsHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private lateinit var newsItemClickListener : ItemClickListener

    var image : ImageView = itemView.findViewById(R.id.item_image)

    override fun onClick(v : View) {
        this.newsItemClickListener.onItemClick(v, layoutPosition)
    }

    fun setItemClickListener(ic : ItemClickListener) {
        this.newsItemClickListener = ic
    }

    interface ItemClickListener {
        fun onItemClick(v : View, pos : Int)
    }
}